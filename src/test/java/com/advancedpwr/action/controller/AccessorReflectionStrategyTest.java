package com.advancedpwr.action.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*/
public class AccessorReflectionStrategyTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testSetPropsFromHash() throws Exception 
	{
		GenericTestObject location = new GenericTestObject();
		Hashtable hash = new Hashtable();
		hash.put("firstName", "chicago");
		hash.put("lastName", "happiness");
		hash.put("nonexistant", "junk");
		
		Scraper scraper = new Scraper();
		AccessorReflectionStrategy strategy = new AccessorReflectionStrategy();
		strategy.setTransformer( new NoOpTransformer() );
		scraper.setPopulationStrategy( strategy );
		scraper.setObject( location );
		scraper.scrape( hash );
		assertTrue("city", location.getFirstName().equals("chicago"));
		assertTrue("name1", location.getLastName().equals("happiness"));
	}
	
	public void testSetPropsFromHash_int() throws Exception 
	{
		PrimitiveBean bean = new PrimitiveBean();
		Hashtable hash = new Hashtable();
		hash.put("int", "1");
		hash.put("long", "2");
		hash.put( "short", "3" );
		hash.put("float", "4.44");
		hash.put("double", "5.55");
		hash.put("boolean", "true");
		hash.put( "character", "char" );
		hash.put( "byte", "abc" );
		hash.put("String", "junk");
		hash.put("BigInteger", "12");
		hash.put("BigDecimal", "13.33");
		
		Scraper scraper = new Scraper();
		AccessorReflectionStrategy strategy = new AccessorReflectionStrategy();
		strategy.setTransformer( new NoOpTransformer() );
		scraper.setPopulationStrategy( strategy );
		scraper.setObject( bean );
		scraper.scrape( hash );
		
		assertEquals( 1, bean.getInt() );
		assertEquals( 2, bean.getLong() );
//		assertEquals( 3, bean.getShort() );
		assertEquals( 4.44f, bean.getFloat() );
		assertEquals( 5.55, bean.getDouble() );
//		assertTrue( bean.isBoolean() );
//		assertEquals( 'c', bean.getChar() );
//		assertEquals( 12, bean.getByte() );
		assertEquals( "junk", bean.getString() );
//		assertEquals( new BigInteger("12"), bean.getBigInteger() );
//		assertEquals( new BigDecimal( "13.33"), bean.getBigInteger() );
	}
	
	public void testSetPropsFromHash_exception() throws Exception 
	{
		GenericTestObject location = new GenericTestObject()
		{

			@Override
			public void setFirstName( String newFirstName )
			{
				throw new NullPointerException();
			}
			
		};
		Hashtable hash = new Hashtable();
		hash.put("firstName", "chicago");
		hash.put("lastName", "happiness");
		hash.put("nonexistant", "junk");
		
		Scraper scraper = new Scraper();
		AccessorReflectionStrategy strategy = new AccessorReflectionStrategy();
		strategy.setTransformer( new NoOpTransformer() );
		scraper.setPopulationStrategy( strategy );
		scraper.setObject( location );
		try
		{
			scraper.scrape( hash );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause() instanceof InvocationTargetException );
		}
		
	}
	
}
