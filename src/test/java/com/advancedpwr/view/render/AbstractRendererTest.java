package com.advancedpwr.view.render;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*/
public abstract class AbstractRendererTest extends TestCase
{

	PrintStream out;
	protected ByteArrayOutputStream output;
	protected ResourceViewRenderer renderer;

	protected void setUp() throws Exception
	{
		
		super.setUp();
		output = new ByteArrayOutputStream();
		out = new PrintStream( output );
		renderer = new ResourceViewRenderer();
		renderer.setPrintStream( out );
		renderer.setExtension( ".html" );
	}

	protected void assertOutput( String expected )
	{
		assertEquals( expected.replaceAll( "\r\n", "\n"), output.toString().replaceAll( "\r\n", "\n") );
	}

}
