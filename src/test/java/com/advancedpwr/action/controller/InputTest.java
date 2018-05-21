/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Oct 6, 2011
 *
 */
public class InputTest extends TestCase
{
	public void testIntValue()
	{
		Map map = new HashMap();
		map.put( "key", "5" );
		Input input = new Input( map, "key" );
		assertEquals( 5, input.intValue() );
		
		input = new Input( map, "missing" );
		assertEquals( 0, input.intValue() );
	}
	
	public void testLongValue()
	{
		Map map = new HashMap();
		map.put( "key", "5" );
		Input input = new Input( map, "key" );
		assertEquals( 5, input.longValue() );
		input = new Input( map, "missing" );
		assertEquals( 0, input.longValue() );
		
	}
	
	public void testDoubleValue()
	{
		Map map = new HashMap();
		map.put( "key", "5.3" );
		Input input = new Input( map, "key" );
		assertEquals( 5.3, input.doubleValue() );

		input = new Input( map, "missing" );
		assertEquals( 0.0, input.doubleValue() );

	}
	
	public void testFloatValue()
	{
		Map map = new HashMap();
		map.put( "key", "5.3" );
		Input input = new Input( map, "key" );
		assertEquals( 5.3f, input.floatValue() );

		input = new Input( map, "missing" );
		assertEquals( 0.0f, input.floatValue() );

	}
	
	public void testBooleanValue()
	{
		Map map = new HashMap();
		map.put( "key", "true" );
		Input input = new Input( map, "key" );
		assertTrue(  input.booleanValue() );
		
		map.put( "key", " " );
		input = new Input( map, "key" );
		assertFalse(  input.booleanValue() );
		
		input = new Input( map, "missing" );
		assertFalse(  input.booleanValue() );
	}
}
