/**
 * 
 */
package com.advancedpwr.view.tags;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Jan 25, 2013
 *
 */
public class AttributeTest extends TestCase
{
	public void testAttribute()
	{
		Attribute attribute = new Attribute();
		attribute.setName( "media" );
		assertEquals( "", attribute.toString() );
		attribute.setValue( "screen" );
		assertEquals( "media=\"screen\"", attribute.toString() );
	}
}
