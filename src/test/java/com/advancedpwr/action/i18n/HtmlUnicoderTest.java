package com.advancedpwr.action.i18n;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 22, 2011
*/
public class HtmlUnicoderTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testUnicoder() throws Exception
	{
		//File encoding prevents us from using real Japanese character internally, so create it this way
		String japaneseCharacter = new String( new byte[]{ -29, -127, -118 }, "UTF8" );
		HtmlUnicoder unicoder = new HtmlUnicoder( japaneseCharacter );
		assertEquals( "&#12362;", unicoder.toEntities() );
		
		japaneseCharacter = new String ( new byte[]{ -27, -127, -67 }, "UTF8");
		
		unicoder = new HtmlUnicoder( japaneseCharacter );
		assertEquals( "&#20605;", unicoder.toEntities() );
		
		unicoder = new HtmlUnicoder( null );
		assertEquals( "", unicoder.toEntities() );
	}

	protected void printBytes( String inString ) throws Exception
	{
		byte[] utf8Bytes = inString.getBytes( "UTF8" );	
		System.out.print( "new byte[]{ ");
		for ( int i = 0; i < utf8Bytes.length; i++ )
		{
			System.out.print( utf8Bytes[i] + ", ");
		}
		System.out.print( "}");
	}
}
