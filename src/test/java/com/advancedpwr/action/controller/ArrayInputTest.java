/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Oct 19, 2011
 *
 */
public class ArrayInputTest extends TestCase
{
	public void testArrayInput()
	{
		Map map = new HashMap();
		map.put( "sku", new String[]{ "SE289-ENG", "SE500-ENG" } );
		map.put( "user", "vzmd2b" );
		ArrayInput input = new ArrayInput( map, "sku" );
		assertTrue( input.exists() );
		assertEquals( "SE289-ENG", input.getValue() );
		input.setIndex( 1 );
		assertTrue( input.exists() );
		assertEquals( "SE500-ENG", input.getValue() );
		
		input.setIndex( 2 );
		assertFalse( input.exists() );
		assertNull( input.getValue() );
	}
}
