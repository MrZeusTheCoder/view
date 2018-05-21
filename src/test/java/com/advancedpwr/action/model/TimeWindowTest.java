package com.advancedpwr.action.model;

import com.advancedpwr.action.controller.TimeWindow;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Nov 1, 2010
*/
public class TimeWindowTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testIsWindowValid() throws Exception
	{
		TimeWindow window = new TimeWindow();
		assertFalse( window.isWindowValid() );
		window.setWindow( 1 );
		assertTrue( window.isWindowValid() );
		Thread.sleep( 1100 );
		assertFalse( window.isWindowValid() );
		
	}
}
