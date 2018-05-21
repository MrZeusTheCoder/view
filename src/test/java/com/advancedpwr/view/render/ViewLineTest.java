package com.advancedpwr.view.render;

import java.util.List;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 27, 2011
*/
public class ViewLineTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testPieces()
	{
		String source = "\t\t<input type=\"$view.type()\" name=\"$i18n.name()\" value=\"$view.value( )\" class=\"foo\" />\t";
		ViewLine line = new ViewLine( source );
		List pieces = line.pieces();
		assertEquals( "\t\t", line.indent() );
		assertEquals( "<input type=\"", pieces.get(0)); 
		assertEquals( "$view.type()", pieces.get(1)); 
		assertEquals( "\" name=\"", pieces.get(2)); 
		assertEquals( "$i18n.name()", pieces.get(3)); 
		assertEquals( "\" value=\"", pieces.get(4)); 
		assertEquals( "$view.value( )", pieces.get(5)); 
		assertEquals( "\" class=\"foo\" />\t", pieces.get(6) );
		
	}
}
