/**
 * 
 */
package com.advancedpwr.view.filter;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 13, 2012
 *
 */
public class MimeTypeMapTest extends TestCase
{
	public void testType()
	{
		MimeTypeMap map = new MimeTypeMap();
		assertEquals( "text/html", map.type( ".html" ) );
		assertEquals( "text/css", map.type( ".css" ) );
		assertEquals( "text/javascript", map.type( ".js" ) );
		assertEquals( "text/html", map.type(  ".dunno" ) );
	}
}
