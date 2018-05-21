package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class EncryptedRequestParametersTest extends TestCase
{

	public void testParameters()
	{
		CustomerBean bean = new CustomerBean();
		bean.email = "5+3=";
		
		RequestParameters params = new EncryptedRequestParameters();
		params.addObject( bean );
		
		Map inputs = new HashMap();
		URLUtility util = new URLUtility();
		String result = util.decode( params.parameters() );
		assertTrue( result.startsWith( "?c=" ) );
		inputs.put( EncryptedParameters.KEY, result.substring( 3 ) );
		EncryptedParameters ep = new EncryptedParameters( inputs );
		assertEquals( "5+3=", ep.getParameters().get( "email" ) );
	}

}
