package com.advancedpwr.view.render;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.advancedpwr.view.examples.ExceptionExample;
import com.advancedpwr.view.examples.I18nResource;
import com.advancedpwr.view.examples.ListExample;
import com.advancedpwr.view.examples.PriceTable;
import com.advancedpwr.view.examples.SampleView;
import com.advancedpwr.view.examples.SubClassExample1;
import com.advancedpwr.view.examples.SubClassExample2;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 27, 2011
*/
public class ResourceViewRendererTest extends AbstractRendererTest
{
	
	public void testRender() throws Exception
	{
		SampleView view = new SampleView();
		renderer.setView( view );
		renderer.render();
		
		assertEquals( "<form id=\"imageForm\" name=\"imageForm\" method=\"post\">\n" + 
				"	<div id=\"cartStep\" class=\"step\">\n" + 
				"	\n" + 
				"		<input type=\"hidden\" name=\"ACTION\" value=\"start\"/>\n" + 
				"		\n" + 
				"		include proposal summary view here\n" + 
				"		<div>\n" + 
				"			<table>\n" + 
				"				<tr>\n" + 
				"				<td>Item</td>\n" + 
				"				<td>Price</td>\n" + 
				"				</tr>\n" + 
				"				<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"			</table>\n" + 
				"		</div>\n" + 
				"		\n" + 
				"		\n" + 
				"\n" +
				"		\n" + 
				"\n" + 
				"		<div class=\"form_row\">\n" + 
				"			<input value=\"Update\" title=\"Update\" type=\"button\" class=\"generic\" onclick=\"continueFunc( this, '$view.updateAction()' );\"/>\n" + 
				"			<input value=\"Continue\" title=\"Continue\" type=\"submit\" class=\"generic\"\n" + 
				"		           onclick=\"continueFunc( this,'$view.billingAction()' );\" />\n" + 
				"		</div>\n" + 
				"	</div>\n" + 
				"</form>", output.toString() );
		System.out.println( output.toString() );
		
	}
	
	public void testRender_subclass_1() throws IOException
	{
		SubClassExample1 view = new SubClassExample1();
		renderer.setView( view );
		renderer.render();
		assertEquals( "<table>\n" + 
				"	<tr>\n" + 
				"	<td>Item</td>\n" + 
				"	<td>Price</td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"	<td>Car</td>\n" + 
				"	<td>$10,000.00</td>\n" + 
				"</tr>\n" + 
				"</table>", output.toString() );
	}
	
	public void testRender_subclass_2() throws IOException
	{
		SubClassExample2 view = new SubClassExample2();
		renderer.setView( view );
		renderer.render();
		assertEquals( "<table class=\"fancytable\">\n" + 
				"	<tr>\n" + 
				"	<td>Fabulous Item</td>\n" + 
				"	<td>Discount Price</td>\n" + 
				"	</tr>\n" + 
				"	<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"</table>", output.toString() );
	}
	
	public void testRender_with_exception_1() throws Exception
	{
		ExceptionExample view = new ExceptionExample();
		renderer.setView( view );

		renderer.render();
		String expected =  
				"<form id=\"imageForm\" name=\"imageForm\" method=\"post\">\n" + 
				"	<div id=\"cartStep\" class=\"step\">\n" + 
				"	\n" + 
				"		<input type=\"hidden\" name=\"ACTION\" value=\"start\"/>\n" + 
				"		\n" + 
				"		include proposal summary view here\n" + 
				"		<div>\n" + 
				"			<table>\n" + 
				"				<tr>\n" + 
				"				<td>Item</td>\n" + 
				"				<td>Price</td>\n" + 
				"				</tr>\n" + 
				"				<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"			</table>\n" + 
				"		</div>\n" + 
				"		\n" + 
				"com.advancedpwr.view.render.InterpreterException: Exception rendering com.advancedpwr.view.examples.ExceptionExample.aVoidMethod(), line 11 of SampleView.html\n";
		assertTrue( output.toString().replaceAll( "\r\n", "\n").startsWith( expected.replaceAll( "\r\n", "\n" ) ) );
	}
	
	public void testRender_with_exception_2() throws Exception
	{
		ExceptionExample view = new ExceptionExample();
		renderer.setView( view );
		renderer.setExceptionHandler( new BasicExceptionHandler() );
		try
		{
			renderer.render();
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertEquals( "Exception rendering com.advancedpwr.view.examples.ExceptionExample.aVoidMethod(), line 11 of SampleView.html", e.getMessage() );
		}
		String expected =  
				"<form id=\"imageForm\" name=\"imageForm\" method=\"post\">\n" + 
				"	<div id=\"cartStep\" class=\"step\">\n" + 
				"	\n" + 
				"		<input type=\"hidden\" name=\"ACTION\" value=\"start\"/>\n" + 
				"		\n" + 
				"		include proposal summary view here\n" + 
				"		<div>\n" + 
				"			<table>\n" + 
				"				<tr>\n" + 
				"				<td>Item</td>\n" + 
				"				<td>Price</td>\n" + 
				"				</tr>\n" + 
				"				<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"			</table>\n" + 
				"		</div>\n" + 
				"		\n" + 
				"com.advancedpwr.view.render.InterpreterException: Exception rendering com.advancedpwr.view.examples.ExceptionExample.aVoidMethod(), line 11 of SampleView.html\n";
		assertTrue( output.toString().replaceAll( "\r\n", "\n").startsWith( expected.replaceAll( "\r\n", "\n" ) ) );
	}
	
	public void testRender_w_no_resource() throws Exception
	{
		I18nResource view = new I18nResource();
		renderer.setView( view );
		renderer.render();
		assertEquals( view.toString(), output.toString() );
	}
	
	public void testRender_list() throws Exception
	{
		ListExample view = new ListExample();
		renderer.setView( view );
		renderer.render();
		assertEquals( "<table>\n" + 
				"	<tr>\n" + 
				"	<td>Car</td>\n" + 
				"	<td>Price</td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"	<td>Cavalier</td>\n" + 
				"	<td>$10,000.00</td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"	<td>Jeep</td>\n" + 
				"	<td>$15,000.00</td>\n" + 
				"	</tr>\n" + 
				"	<tr>\n" + 
				"	<td>F-150</td>\n" + 
				"	<td>$20,000.00</td>\n" + 
				"	</tr>\n" + 
				"</table>", output.toString() );
		System.out.print( output.toString() );
	}
	
	public void testSetIndent() throws Exception
	{
		PriceTable view = new PriceTable();
		renderer.setView( view );
		renderer.setIndent( "	" );
		renderer.render();
		assertEquals( "	<table>\n" + 
				"		<tr>\n" + 
				"		<td>Item</td>\n" + 
				"		<td>Price</td>\n" + 
				"		</tr>\n" + 
				"		<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"	</table>", output.toString() );
	}
	
	public void testRender_w_listener() throws Exception
	{
		PriceTable view = new PriceTable();
		renderer.setView( view );
		renderer.setIndent( "" );
		final List renderedObjects = new ArrayList();
		renderer.setListener( new RenderingListener()
		{
			public void renderEvent( Object inResult )
			{
				renderedObjects.add( inResult );
			}
		});
		renderer.render();
		assertEquals( "<table>\n" + 
				"	<tr>\n" + 
				"	<td>Item</td>\n" + 
				"	<td>Price</td>\n" + 
				"	</tr>\n" + 
				"	<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"</table>", output.toString() );
		
		assertEquals( 7, renderedObjects.size() );
	}
	
	public void testSetInterpreter()
	{
		PriceTable view = new PriceTable();
		renderer.setView( view );
		renderer.setIndent( "	" );
		renderer.setInterpreter( new Interpreter()
		{
			public void interpret( InputStream in ) throws IOException
			{
				Charset utf8 = Charset.availableCharsets().get( "UTF-8" );
				BufferedReader reader = new BufferedReader( new InputStreamReader( in, utf8 ) );
				while ( reader.ready() )
				{
					renderer.getPrintStream().println( reader.readLine() );
				}
			}
		} );
		renderer.render();
		assertEquals( "<table>\n" + 
				"	<tr>\n" + 
				"	<td>Item</td>\n" + 
				"	<td>Price</td>\n" + 
				"	</tr>\n" + 
				"	$view.tableRows()\n" + 
				"</table>\n", output.toString() );
		
	}
	
}
