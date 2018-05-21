package com.advancedpwr.action.controller;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 29, 2011
*/
public class ActionTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testHasAction()
	{
		Action action = new Action();
		assertFalse( action.hasAction() );
		assertNull( action.getActionMethod() );
		action.ACTION = "start";
		assertTrue( action.hasAction() );
		assertEquals( "start", action.getActionMethod() );
		
		action.setACTION( "next" );
		assertEquals( "next", action.getActionMethod() );
	}


}
