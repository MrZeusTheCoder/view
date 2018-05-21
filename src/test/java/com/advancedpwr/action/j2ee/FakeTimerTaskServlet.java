/**
 * 
 */
package com.advancedpwr.action.j2ee;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 12, 2013
 *
 */
public class FakeTimerTaskServlet extends TimerTaskServlet
{
	protected List fieldEvents;
	public FakeTimerControls fakeTimerControls = new FakeTimerControls();
	/* (non-Javadoc)
	 * @see com.advancedpwr.action.j2ee.TimerTaskServlet#purpose()
	 */
	protected String purpose()
	{
		return "Fake task";
	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.action.j2ee.TimerTaskServlet#createTask()
	 */
	protected TimerTask createTask()
	{
		return new TimerTask()
		{	
			public void run()
			{
				getEvents().add( "A fake task was run" );
			}
		};
	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.action.j2ee.TimerTaskServlet#timerControls()
	 */
	protected TimerControls timerControls()
	{
		return fakeTimerControls;
	}

	public List getEvents()
	{
		if ( fieldEvents == null )
		{
			fieldEvents = new ArrayList();
		}

		return fieldEvents;
	}
}
