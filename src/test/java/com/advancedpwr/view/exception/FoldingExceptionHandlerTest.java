package com.advancedpwr.view.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 27, 2011
*/
public class FoldingExceptionHandlerTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testHandleException()
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream( out );
		FoldingExceptionHandler handler = new FoldingExceptionHandler();
		handler.handleException( stream, new Exception( "Bad stuff!" ) );
		assertTrue( out.toString().contains( "<pre>java.lang.Exception: Bad stuff!" ) );
	}
	
	public void testSetExceptionView()
	{
		ExceptionView view = new ExceptionView();
		FoldingExceptionHandler handler = new FoldingExceptionHandler();
		handler.setExceptionView( view );
		assertEquals( view, handler.getExceptionView() );
	}
}
