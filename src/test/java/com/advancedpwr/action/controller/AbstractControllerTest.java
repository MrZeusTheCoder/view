package com.advancedpwr.action.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import junit.framework.TestCase;
/**
*  @author Matthew Avery, mavery@advancedpwr.com on Aug 18, 2010
*/
public class AbstractControllerTest extends TestCase
{
	AbstractController controller;

	class ExceptionHolder
	{
		public Exception e;
	}
	
	protected void setUp() throws Exception
	{
		super.setUp();
		
		controller = new AbstractController()
		{
		};
	}

	public void testForward() throws Exception
	{
		PageContext mockPageContext = mock( PageContext.class );
		HttpServletRequest mockRequest = mock( HttpServletRequest.class );
		when( mockPageContext.getRequest() ).thenReturn( mockRequest );
		
		controller.setPageContext( mockPageContext );
		controller.forward( "/page.jsp" );

		verify( mockPageContext ).forward( "/page.jsp" );
	}
	
	public void testForward_exception() throws Exception
	{
		PageContext mockPageContext = mock( PageContext.class );
		HttpServletRequest mockRequest = mock( HttpServletRequest.class );
		when( mockPageContext.getRequest() ).thenReturn( mockRequest );

		doThrow( new IOException("Bad Stuff!" ) ).when( mockPageContext ).forward( "/page.jsp" );
		controller.setPageContext( mockPageContext );
		controller.forward( "/page.jsp" );

		verify( mockPageContext ).forward( "/page.jsp" );
	}

//	public void testForward_exception() throws Exception
//	{
//		PageContext mockPageContext = mock( PageContext.class );
//		IOException e = new IOException( "bad stuff");
//		doThrow( e ).when( mockPageContext ).forward( "/page.jsp" );
//
//		controller.setPageContext( mockPageContext );
//		controller.forward( "/page.jsp" );
//
//		verify( mockPageContext ).forward( "/page.jsp" );
//		verify( mockPageContext ).handlePageException( (Throwable)e );
//	}

	public void testScrape()
	{
		PageContext pageContext = mock( PageContext.class );
		HttpServletRequest request = mock( HttpServletRequest.class );
		when( pageContext.getRequest() ).thenReturn( request );
		Map params = new HashMap();
		params.put(  "ACTION", "addProduct" );
		when( request.getParameterMap() ).thenReturn( params );
		controller.setPageContext( pageContext );

		Action action = new Action();

		controller.scrape( action );

		assertTrue( action.hasAction() );
		assertEquals( "addProduct", action.getActionMethod() );

	}

	public void testDoAction()
	{
		class BooleanHolder
		{
			boolean flag;
		}

		final BooleanHolder holder = new BooleanHolder();

		controller = new AbstractController()
		{
			public void addProduct()
			{
				holder.flag = true;
			}
		};

		controller.setActionMap( new ActionMap() );
		PageContext pageContext = mock( PageContext.class );
		HttpServletRequest request = mock( HttpServletRequest.class );
		when( pageContext.getRequest() ).thenReturn( request );
		Map params = new HashMap();
		params.put(  "ACTION", "addProduct" );
		when( request.getParameterMap() ).thenReturn( params );
		controller.setPageContext( pageContext );

		assertFalse( holder.flag );
		controller.doAction();
		assertTrue( holder.flag );


	}
	
	public void testDelegates()
	{
		ViewManager manager = mock( ViewManager.class );
		controller = new AbstractController()
		{
		};
		
		controller.setViewManager( manager );
		
		controller.renderHtml( null );
		controller.renderJavascript( null );
		controller.previous();
		controller.setLayout( null );
		controller.setExceptionHandler( null );
		controller.handleException( null );
		
		verify( manager ).renderHtml( null );
		verify( manager ).renderJavascript( null );
		verify( manager ).previous();
		verify( manager ).setLayout( null );
		verify( manager ).setExceptionHandler( null );
		verify( manager ).handleException( null );
	}

	public void testGetResponse()
	{
		PageContext mockPageContext = mock( PageContext.class );
		HttpServletResponse response = mock( HttpServletResponse.class );
		when( mockPageContext.getResponse() ).thenReturn( response );
		HttpServletRequest mockRequest = mock( HttpServletRequest.class );
		when( mockPageContext.getRequest() ).thenReturn( mockRequest );

		
		controller.setPageContext( mockPageContext );
		
		assertEquals( response, controller.getResponse() );
	}

	public void testRedirect() throws Exception
	{
		PageContext mockPageContext = mock( PageContext.class );
		HttpServletResponse response = mock( HttpServletResponse.class );
		when( mockPageContext.getResponse() ).thenReturn( response );
		HttpServletRequest mockRequest = mock( HttpServletRequest.class );
		when( mockPageContext.getRequest() ).thenReturn( mockRequest );

		controller.setPageContext( mockPageContext );
		controller.redirect( "/path" );
		verify( response ).sendRedirect( "/path" );
		
	}
	
	public void testRedirect_exception() throws Exception
	{
		PageContext mockPageContext = mock( PageContext.class );
		HttpServletResponse response = mock( HttpServletResponse.class );
		when( mockPageContext.getResponse() ).thenReturn( response );
		doThrow( new IOException("Bad Stuff!") ).when( response ).sendRedirect( "/path" );
		HttpServletRequest mockRequest = mock( HttpServletRequest.class );
		when( mockPageContext.getRequest() ).thenReturn( mockRequest );

		
		final ExceptionHolder holder = new ExceptionHolder();
		controller = new AbstractController()
		{

			@Override
			protected void handleException( Exception e )
			{
				holder.e = e;
			}
			
			
		};
		controller.setPageContext( mockPageContext );
		assertNull( holder.e );
		controller.redirect( "/path" );;
		assertTrue( holder.e.getMessage().contains( "Bad Stuff!" ) );
		
	}
	
	public void testExecuteLiveAction_exception()
	{
		ActionMap actionMap = mock( ActionMap.class );
		RuntimeException re = new RuntimeException();
		doThrow( re ).when( actionMap ).executeLiveAction( null );
		final ExceptionHolder holder = new ExceptionHolder();
		controller = new AbstractController()
		{

			@Override
			protected void handleException( Exception e )
			{
				holder.e = e;
			}
			
			
		};
		controller.setActionMap( actionMap );
		
		assertNull( holder.e );
		controller.executeLiveAction( null );
		assertEquals( re, holder.e );
	}
	
}
