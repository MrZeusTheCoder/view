package com.advancedpwr.action.i18n;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Aug 25, 2010
*/
public abstract class Localizer
{
	private static final String USD = "USD";

	protected Locale fieldLocale;
	
	protected String fieldCurrencyCode;
	
	protected CurrencyFormat fieldCurrencyFormat;

	public Locale getLocale()
	{
		if ( fieldLocale == null )
		{
			fieldLocale = Locale.getDefault();
		}
		return fieldLocale;
	}

	public void setLocale( Locale locale )
	{
		fieldLocale = locale;
		resetCurrencyFormat();
	}

	public ResourceBundle getResourceBundle()
	{
		return ResourceBundle.getBundle( getBaseName(), getLocale() );
	}

	public String getBaseName()
	{
		return getClass().getName();
	}

	public String getString( String inKey )
	{
		try
		{
			return utf8( getResourceBundle().getString( inKey ) );
		}
		catch ( MissingResourceException e )
		{
			return inKey;
		}
	}
	
	protected String utf8( String value )
	{
		return new String( value.getBytes( Charset.forName( "ISO-8859-1" ) ), Charset.forName( "UTF-8" ) );
	}

	public String getCurrencyCode()
	{
		if ( fieldCurrencyCode == null )
		{
			fieldCurrencyCode = USD;
		}
		return fieldCurrencyCode;
	}

	public void setCurrencyCode( String currencyCode )
	{
		fieldCurrencyCode = currencyCode;
		resetCurrencyFormat();
	}

	protected void resetCurrencyFormat()
	{
		fieldCurrencyFormat = null;
	}

	public CurrencyFormat getCurrencyFormat()
	{
		if ( fieldCurrencyFormat == null )
		{
			fieldCurrencyFormat = createCurrencyFormat();
		}
		return fieldCurrencyFormat;
	}

	protected CurrencyFormat createCurrencyFormat()
	{
		return new CurrencyFormat( getLocale(), getCurrencyCode() );
	}

}
