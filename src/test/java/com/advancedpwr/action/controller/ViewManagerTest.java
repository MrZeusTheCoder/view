package com.advancedpwr.action.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.jsp.PageContext;

import com.advancedpwr.view.examples.PriceTable;
import com.advancedpwr.view.layout.Layout;
import com.advancedpwr.view.layout.SimpleLayout;
import com.advancedpwr.view.render.ResourceViewRenderer;

import junit.framework.TestCase;
/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 29, 2011
*/
public class ViewManagerTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	public void testHandleException_exception() throws Exception
	{
		Exception e = new Exception( "Bad stuff" );
		IOException e2 = new IOException( "internal bad stuff" );
		PageContext pageContext = mock( PageContext.class );
		doThrow( e2 ).when( pageContext ).handlePageException( any( Throwable.class ) );
		ServletResponse response = new ServletResponseStub();
		when( pageContext.getResponse() ).thenReturn( response );
		class ExceptionHolder
		{
			Throwable t;
		}
		final ExceptionHolder holder = new ExceptionHolder();
		ViewManager viewmanager = new ViewManager()
		{
			protected void error( Throwable t )
			{
				super.error( t );
				holder.t = t;
			}
		};
		viewmanager.setExceptionHandler( new DoNothingExceptionHandler() );
		viewmanager.setPageContext( pageContext );

		assertNull( holder.t );
		viewmanager.handleException( e );
		assertEquals( e, holder.t );

	}
	
	public void testHandleException_ioexception() throws Exception
	{
		Exception e = new Exception( "Bad stuff" );
		final IOException e2 = new IOException( "internal bad stuff" );
		PageContext pageContext = mock( PageContext.class );
		doThrow( e2 ).when( pageContext ).handlePageException( any( Throwable.class ) );
		ServletResponse response = new ServletResponseStub();
		when( pageContext.getResponse() ).thenReturn( response );
		class ExceptionHolder
		{
			Throwable t;
		}
		final ExceptionHolder holder = new ExceptionHolder();
		ViewManager viewmanager = new ViewManager()
		{
			protected void error( Throwable t )
			{
				super.error( t );
				holder.t = t;
			}

			@Override
			protected PrintStream createPrintStream() throws IOException
			{
				throw e2;
			}
			
		};
		viewmanager.setExceptionHandler( new DoNothingExceptionHandler() );
		viewmanager.setPageContext( pageContext );

		assertNull( holder.t );
		try
		{
			viewmanager.handleException( e );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e3 )
		{
			assertTrue( e3.getCause().equals( e2 ) );
			assertEquals( e2, holder.t );
		}
		

	}

	public void testRenderHtml()
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		final PrintStream out = new PrintStream( output );
		DoNothingExceptionHandler dneh = new DoNothingExceptionHandler();
		final List holder = new ArrayList();
		ViewManager viewmanager = new ViewManager()
		{
			protected PrintStream createPrintStream()
			{
				return out;
			}
			
			protected void renderToStream( ResourceViewRenderer renderer )
			{
				holder.add( renderer );
				super.renderToStream( renderer );
			}
		};
		viewmanager.setExceptionHandler( dneh );
		Layout layout = new SimpleLayout();
		viewmanager.setLayout( layout );
		PriceTable priceTable = new PriceTable();
		assertEquals( 0, holder.size() );
		viewmanager.renderHtml( priceTable );
		assertEquals( "<html>\n" + 
				"<body>\n" + 
				"  <table>\n" + 
				"  	<tr>\n" + 
				"  	<td>Item</td>\n" + 
				"  	<td>Price</td>\n" + 
				"  	</tr>\n" + 
				"  	<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"  </table>\n" + 
				"</body>\n" + 
				"</html>", output.toString() );
		assertEquals( priceTable, viewmanager.getViews().get( 0 ) );
		ResourceViewRenderer renderer = (ResourceViewRenderer)holder.get( 0 );
		assertEquals(dneh, renderer.getExceptionHandler());
		
		output.reset();
		
		viewmanager.previous();
		assertEquals( "<html>\n" + 
				"<body>\n" + 
				"  <table>\n" + 
				"  	<tr>\n" + 
				"  	<td>Item</td>\n" + 
				"  	<td>Price</td>\n" + 
				"  	</tr>\n" + 
				"  	<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"  </table>\n" + 
				"</body>\n" + 
				"</html>", output.toString() );
	}
	
	public void testRenderToStream_ioexception()
	{
		class Holder
		{
			public Exception e;
		}
		final Holder holder = new Holder();
		ViewManager viewmanager = new ViewManager()
		{
			protected PrintStream createPrintStream() throws IOException
			{
				throw new IOException( "Bad Stuff!" );
			}

			@Override
			protected void handleException( Exception e )
			{
				holder.e = e;
			}
			
		};
		viewmanager.setExceptionHandler( new DoNothingExceptionHandler() );
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		assertNull( holder.e );
		viewmanager.renderToStream( renderer );
		assertTrue( holder.e.getMessage().contains( "Bad Stuff!" ) );
	
	}
	
	public void testRenderJavascript()
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		final PrintStream out = new PrintStream( output );

		ViewManager viewmanager = new ViewManager()
		{
			protected PrintStream createPrintStream()
			{
				return out;
			}
		};
		Layout layout = new SimpleLayout();
		viewmanager.setLayout( layout );
		PriceTable priceTable = new PriceTable();
		viewmanager.renderJavascript( priceTable );
		assertEquals( "function sortTable()\n" + 
				"{\n" + 
				"	// do something to sort the table\n" + 
				"}", output.toString() );
	}
	
	public void testHasExceptionHandler()
	{
		ViewManager viewmanager = new ViewManager();
		assertFalse( viewmanager.hasExceptionHandler() );
		viewmanager.setExceptionHandler( new DoNothingExceptionHandler() );
		assertTrue( viewmanager.hasExceptionHandler() );
	}
}
