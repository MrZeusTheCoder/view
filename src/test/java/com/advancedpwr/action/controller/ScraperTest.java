package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jul 13, 2010
*/
public class ScraperTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testScrape()
	{
		Scraper scraper = new Scraper();
		CustomerBean CustomerBean = new CustomerBean();
		assertNull( CustomerBean.getFirstName() );

		scraper.setObject( CustomerBean );

		HashMap inputs = new HashMap();
		inputs.put( "recaptcha_challenge_field", "hello" );
		inputs.put(  "firstName", "Jack" );
		inputs.put( "age", "5" );

		scraper.scrape( inputs );

		assertEquals( "Jack", CustomerBean.getFirstName() );
		assertEquals( 5, CustomerBean.getAge() );
	}
	
	public void testScrape_bad_int()
	{
		Scraper scraper = new Scraper();
		CustomerBean CustomerBean = new CustomerBean();
		assertNull( CustomerBean.getFirstName() );

		scraper.setObject( CustomerBean );

		HashMap inputs = new HashMap();
		inputs.put( "recaptcha_challenge_field", "hello" );
		inputs.put(  "firstName", "Jack" );
		inputs.put( "age", "old" );

		try
		{
			scraper.scrape( inputs );;
			fail( "Should have thrown an Exception" );
		}
		catch( RuntimeException e )
		{
			assertTrue( e.getCause() instanceof NumberFormatException );
		}

	}
	
	public void testScrape_array()
	{
		Scraper scraper = new Scraper();
		CustomerBean CustomerBean = new CustomerBean();
		assertNull( CustomerBean.getFirstName() );

		scraper.setObject( CustomerBean );

		HashMap inputs = new HashMap();
		inputs.put( "recaptcha_challenge_field", "hello" );
		inputs.put(  "firstName", new String[]{"Joe", "Jack"} );

		scraper.scrape( inputs );

		assertEquals( "Joe", CustomerBean.getFirstName() );
	}

//	public void testScrape_with_validation()
//	{
//		Scraper scraper = new Scraper();
//		scraper.addValidator( new EmailValidator() );
//		CustomerBean CustomerBean = new CustomerBean();
//		assertNull( CustomerBean.getEmail() );
//
//		scraper.setObject( CustomerBean );
//
//		HashMap inputs = new HashMap();
//		inputs.put( "email", "james.kirk@enterprise.com" );
//
//		scraper.scrape( inputs );
//
//		assertEquals( "james.kirk@enterprise.com", CustomerBean.getEmail() );
//		assertTrue( scraper.isValid() );
//	}
//
//	public void testScrape_with_validation_fail()
//	{
//		Scraper scraper = new Scraper();
//		scraper.addValidator( new EmailValidator() );
//		CustomerBean CustomerBean = new CustomerBean();
//		assertNull( CustomerBean.getEmail() );
//
//		scraper.setObject( CustomerBean );
//
//		HashMap inputs = new HashMap();
//		inputs.put( "email", "james.kirk!!@enterprise.com" );
//
//		scraper.scrape( inputs );;
//		assertFalse( scraper.isValid() );
//
//		assertEquals( "E-mail address contains invalid characters. Failed value: \"james.kirk!!@enterprise.com\"", scraper.validationFailureMessage() );
//	}
	
	public void testSetValueError()
	{
		PopulationStrategy strategy = new PopulationStrategy()
		{
			
			public void setValue( Object inTarget, Input input )
			{
				throw new RuntimeException( new IllegalAccessException() );
			}
		};
		Scraper scraper = new Scraper();
		scraper.setPopulationStrategy( strategy );
		
		class BadGuy
		{
			public String key;
		}
		scraper.setObject( new BadGuy() );
		Input input = new Input( new HashMap(), "key" );
		try
		{
			scraper.setValue( input );;
			fail( "Should have thrown an Exception" );
		}
		catch( RuntimeException e )
		{
			assertTrue( e.getCause() instanceof IllegalAccessException );
		}
	}
	
	public void testScrape_base64()
	{
		class NavTest
		{
			public String nav_action;
			public String organizationId;
			public String promotion_code;
		}
		Map encoded = new HashMap();
		encoded.put("k", "bmF2X2FjdGlvbj1pbml0LmpzcCZvcmdhbml6YXRpb25JZD1hMVBRMDAwMDAwMEFjbGJNQUMmcHJvbW90aW9uX2NvZGU9U0UyODktU0NO" );
		
		Scraper scraper = new Scraper();
		NavTest navTest = new NavTest();
		scraper.setObject( navTest );
		scraper.scrape( encoded );
		assertEquals( "init.jsp", navTest.nav_action );
		assertEquals( "a1PQ0000000AclbMAC", navTest.organizationId );
		assertEquals( "SE289-SCN", navTest.promotion_code);
		
		encoded.put( "locale", "zh_CN" );
		CustomerBean selector = new CustomerBean();
		scraper.setObject( selector );
		assertNull( selector.locale );
		scraper.scrape( encoded );
		assertEquals( "zh_CN", selector.locale );
	}

}
