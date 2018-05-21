/**
 * 
 */
package com.advancedpwr.action.j2ee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 12, 2013
 *
 */
public abstract class TimerTaskServlet extends HttpServlet
{
	protected Timer fieldTimer;
	
	protected abstract String purpose();
	
	protected void service( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException
	{
		response.setContentType( "text/plain" );
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter writer = response.getWriter();
		writer.println( purpose() + " enabled: " + isEnabled() );
		long minutes =  period() / ( 60 * 1000 );
		writer.println( "Rescheduling " + purpose() + " every " + minutes + " minutes" );
		writer.flush();
		writer.close();
		scheduleTask();
	}

	public void init( ServletConfig config ) throws ServletException
	{
//		super.init( config );
		scheduleTask();
	}

	protected void scheduleTask()
	{
		if ( getTimer() != null )
		{
			getTimer().cancel();
		}
		if ( !isEnabled() )
		{
			return;
		}
		Timer timer = new Timer();
		TimerTask task = createTask();
		timer.schedule( task, 0, period() );
		setTimer( timer );
	}

	protected abstract TimerTask createTask();

	protected abstract TimerControls timerControls();
	
	protected boolean isEnabled()
	{
		return timerControls().isEnabled();
	}

	protected long period()
	{
		return timerControls().period();
	}

	public Timer getTimer()
	{
		return fieldTimer;
	}

	public void setTimer( Timer timer )
	{
		fieldTimer = timer;
	}
}
