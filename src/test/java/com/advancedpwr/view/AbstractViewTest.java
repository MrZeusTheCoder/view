package com.advancedpwr.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;

import com.advancedpwr.view.filter.PageContextAdapter;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 2, 2011
*/
public class AbstractViewTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testGetPageContext()
	{
		HttpServletRequest request = EasyMock.createMock( HttpServletRequest.class );
		HttpServletResponse response = EasyMock.createMock( HttpServletResponse.class );
		EasyMock.replay( request );
		EasyMock.replay( response );
		PageContextAdapter pc = new PageContextAdapter( request, response );
		AbstractView view = new AbstractView( pc )
		{
		};
		assertEquals( pc, view.getPageContext() );
		assertEquals( request, view.getRequest() );
	}

	public void testClearBuffer()
	{
		
		AbstractView view = new AbstractView(){};
		view.append( "TEST" );
		view.clearBuffer();
		
		assertNull(view.fieldBuffer);
	}
}
