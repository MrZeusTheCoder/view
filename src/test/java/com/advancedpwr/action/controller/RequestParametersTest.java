package com.advancedpwr.action.controller;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 4, 2011
*/
public class RequestParametersTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testToString()
	{
		CustomerBean bean = new CustomerBean();
		bean.firstName = "James T.";
		bean.lastName = "Kirk";
		RequestParameters params = new RequestParameters();
		assertEquals( "", params.toString() );
		params.addObject( bean );
		assertEquals( "?firstName=James+T.&lastName=Kirk&age=0", params.toString() );
	}

}
