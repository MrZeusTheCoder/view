package com.advancedpwr.view.exception;

import junit.framework.TestCase;

public class ExceptionViewTest extends TestCase
{
	
	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testAcessors() 
	{
		ExceptionView view = new ExceptionView();
		RuntimeException excp = new RuntimeException();
		view.setException(excp);
		assertTrue(view.getException() == excp);
		ExceptionHeader excpHeader = new ExceptionHeader();
		view.setHeader(excpHeader);
		assertTrue(view.getHeader() == excpHeader);
		view.setPrintStream(System.out);
		assertTrue(view.getPrintStream() == System.out);
		String testString = new String("test");
		view.setFooter(testString);
		assertTrue(view.getFooter() == testString);
	}

}
