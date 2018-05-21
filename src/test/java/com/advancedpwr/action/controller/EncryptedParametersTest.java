package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import com.advancedpwr.action.config.Crypt;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Nov 1, 2010
*/
public class EncryptedParametersTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testParameters()
	{
	
		TimeWindow timeWindow = new TimeWindow();
		timeWindow.setWindow( 3 );
		RequestParameters requestParameters = new RequestParameters();
		requestParameters.addObject( timeWindow );
		System.out.println(requestParameters);
		
		Crypt e = new Crypt();
		String encrypted = e.encrypt( requestParameters.parameters().substring( 1 ) );
		System.out.println( encrypted );
		
		Map inputs = new HashMap();
		inputs.put( EncryptedParameters.KEY, encrypted );
		
		EncryptedParameters params = new EncryptedParameters( inputs );
		assertEquals( timeWindow.end_date, params.getParameters().get( "end_date" ) );
		
		TimeWindow recievedWindow = new TimeWindow();
		Scraper scraper = new Scraper();
		scraper.setObject( recievedWindow );
		scraper.scrape( inputs );
		assertEquals( timeWindow.end_date, recievedWindow.end_date );
		
	}
	
	public void testEncrypt()
	{
		String encrypted = EncryptedParameters.encrypt( "nav_action=init.jsp" );
		assertFalse( encrypted.contains( "nav_action=init.jsp" ) );
		
	}

}