package com.advancedpwr.action.i18n;

import java.math.BigDecimal;
import java.util.Locale;

import junit.framework.TestCase;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 21, 2011
*/
public class CurrencyFormatTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testFormat_BigDecimal() throws Exception
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		System.out.println( "testFormat_BigDecimal");
		System.out.println( format.format( BigDecimal.TEN ) );
		assertEquals( jpy() + "10", format.format( BigDecimal.TEN ) );

		format = new CurrencyFormat( Locale.US, "CAD" );
		System.out.println( format.format( BigDecimal.TEN ) );
		assertEquals( "CAD10.00", format.format( BigDecimal.TEN ) );

		format = new CurrencyFormat( Locale.CANADA, "USD" );
		System.out.println( format.format( BigDecimal.TEN ) );
//		assertEquals( "US$10.00", format.format( BigDecimal.TEN ) );
		
		format = new CurrencyFormat( Locale.US, "JPY" );
		System.out.println( format.format( BigDecimal.TEN ) );
		assertEquals( "JPY10", format.format( BigDecimal.TEN ) );
	}
	
	public void testFormat_double() throws Exception
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		System.out.println( "testFormat_double");
		System.out.println( format.format( 10 ) );
		assertEquals( jpy() + "10", format.format( 10 ) );

		format = new CurrencyFormat( Locale.US, "CAD" );
		System.out.println( format.format( 10 ) );
		assertEquals( "CAD10.00", format.format( 10 ) );

		format = new CurrencyFormat( Locale.CANADA, "USD" );
		System.out.println( format.format( 10 ) );
//		assertEquals( "US$10.00", format.format( 10 ) );
		
		format = new CurrencyFormat( Locale.US, "JPY" );
		System.out.println( format.format( 10 ) );
		assertEquals( "JPY10", format.format( 10 ) );
	}
	
	public void testToHtml_BigDecimal()
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		assertEquals( "&#65509;10", format.toHtml( BigDecimal.TEN ) );
	}
	
	public void testToHtml_double()
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		assertEquals( "&#65509;10", format.toHtml( 10 ) );
	}
	
	public void testToHtml_string()
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		assertEquals( "&#65509;10", format.toHtml( "10.00" ) );
		
		format = new CurrencyFormat( Locale.US, "EUR");
		assertEquals( "&#8364;10.00", format.toHtml( "10.000") );
		
		format = new CurrencyFormat( Locale.GERMANY, "EUR");
		assertEquals( "10,00 &#8364;", format.toHtml( "10.000") );
	}
	
	public void testFormat_String() throws Exception
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		assertEquals( jpy() + "10", format.format( "10.000" ) );
		assertEquals( jpy() + "0", format.format( "" ) );
		
		format = new CurrencyFormat( Locale.US, "EUR");
		assertEquals( "\u20AC10.00", format.format( "10.000") );
		
		format = new CurrencyFormat( Locale.GERMANY, "EUR");
		assertEquals( "10,00 \u20AC", format.format( "10.000") );
	}
	
	public void testFormatNoSymbol_String() throws Exception
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		assertEquals( "10", format.formatNoSymbol( "10.000" ) );
		assertEquals( "0", format.formatNoSymbol( "" ) );
		
		format = new CurrencyFormat( Locale.US, "EUR");
		assertEquals( "10.00", format.formatNoSymbol( "10.000") );
		
		format = new CurrencyFormat( Locale.GERMANY, "EUR");
		assertEquals( "10,00", format.formatNoSymbol( "10.000") );
	}
	
	public void testFormatNoSymbol_BigDecimal() throws Exception
	{
		CurrencyFormat format = new CurrencyFormat( Locale.JAPAN, "JPY" );
		BigDecimal num = new BigDecimal( "10.000" );
		assertEquals( "10", format.formatNoSymbol( num ) );
		assertEquals( "0", format.formatNoSymbol( "" ) );
		
		format = new CurrencyFormat( Locale.US, "EUR");
		assertEquals( "10.00", format.formatNoSymbol( num ) );
		
		format = new CurrencyFormat( Locale.GERMANY, "EUR");
		assertEquals( "10,00", format.formatNoSymbol(  num ) );
	}
	
	protected String jpy() throws Exception
	{
		String symbol = new String(new byte[]{ -17, -65, -91 }, "UTF8");
		return symbol;
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
