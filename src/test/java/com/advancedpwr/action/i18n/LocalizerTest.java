package com.advancedpwr.action.i18n;

import java.util.Locale;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*/
public class LocalizerTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testGetCurrencyFormat()
	{
		Localizer localizer = new MyAppLocalizer();
		
		assertEquals( "USD", localizer.getCurrencyFormat().getCurrencyCode() );
		localizer.setCurrencyCode( "JPY" );
		assertEquals( "JPY", localizer.getCurrencyFormat().getCurrencyCode() );
	}

	public void testResourceBundle()
	{
		Localizer localizer = new MyAppLocalizer();
		assertEquals( "default resource bundle", localizer.getString( "text" ) );
		localizer.setLocale( Locale.CANADA );
		assertEquals( "Canadian resource bundle, ay?", localizer.getString( "text" ) );
		
		assertEquals( "missing.text", localizer.getString( "missing.text" ) );
	}
	
}
