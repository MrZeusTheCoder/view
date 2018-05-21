/**
 * 
 */
package com.advancedpwr.action.j2ee;

import static org.easymock.EasyMock.*;

import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;
/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 12, 2013
 *
 */
public class TimerTaskServletTest extends TestCase
{
	public void testInit() throws Exception
	{
		FakeTimerTaskServlet servlet = new FakeTimerTaskServlet();
		servlet.fakeTimerControls.setEnabledFlag( true );
		servlet.fakeTimerControls.setPeriod( 5 );
		ServletConfig config = createStrictMock( ServletConfig.class );
		replay( config );
		servlet.init( config );
		Thread.sleep( 100 );
		assertTrue( servlet.getEvents().size() > 0 );
	}
	
	public void testService() throws Exception
	{
		FakeTimerTaskServlet servlet = new FakeTimerTaskServlet();
		servlet.fakeTimerControls.setEnabledFlag( true );
		servlet.fakeTimerControls.setPeriod( 10 * 60 * 1000 );
		servlet.init( null );
		HttpServletResponse response = createStrictMock( HttpServletResponse.class );
		response.setContentType( "text/plain" );
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter writer =createStrictMock( PrintWriter.class );
		writer.println( "Fake task enabled: true");
		writer.println( "Rescheduling Fake task every 10 minutes");
		writer.flush();
		writer.close();
		replay( writer );
		expect( response.getWriter() ).andReturn( writer );
		replay( response );

		servlet.service( null, response );
	}
	
	public void testService_disabled() throws Exception
	{
		FakeTimerTaskServlet servlet = new FakeTimerTaskServlet();
		servlet.fakeTimerControls.setEnabledFlag( false );
		servlet.fakeTimerControls.setPeriod( 10 * 60 * 1000 );
		servlet.init( null );
		HttpServletResponse response = createStrictMock( HttpServletResponse.class );
		response.setContentType( "text/plain" );
		response.setCharacterEncoding( "UTF-8" );
		PrintWriter writer =createStrictMock( PrintWriter.class );
		writer.println( "Fake task enabled: false");
		writer.println( "Rescheduling Fake task every 10 minutes");
		writer.flush();
		writer.close();
		replay( writer );
		expect( response.getWriter() ).andReturn( writer );
		replay( response );

		servlet.service( null, response );
	}
	
}
