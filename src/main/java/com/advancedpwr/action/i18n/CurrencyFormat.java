package com.advancedpwr.action.i18n;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
//import be.arci.math.BigDecimal;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 21, 2011
*/
public class CurrencyFormat extends DecimalFormat
{
	protected Locale fieldLocale;
	protected String fieldCurrencyCode;
	
	public CurrencyFormat( Locale inLocale, String inCurrencyCode )
	{
		super( createPattern( inLocale ) );
		setLocale( inLocale );
		setCurrencyCode( inCurrencyCode );
		Currency currency = createCurrency();
		DecimalFormatSymbols symbols = createSymbols( currency );
		setDecimalFormatSymbols( symbols );
		setMaximumFractionDigits( currency.getDefaultFractionDigits() );
	}
	
	public String toHtml( BigDecimal inAmount )
	{
		return new HtmlUnicoder( format( inAmount ) ).toEntities();
	}
	
	public String toHtml( double inAmount )
	{
		return new HtmlUnicoder( format( inAmount ) ).toEntities();
	}
	
	public String toHtml( String inAmount )
	{
		return new HtmlUnicoder( format( inAmount ) ).toEntities();
	}

	public String format( BigDecimal inAmount )
	{
		return format( inAmount.doubleValue() );
	}
	
	public String format( String inAmount )
	{
		return format( toAmount( inAmount ) );
	}

	protected double toAmount( String inAmount )
	{
		if ( inAmount == null || inAmount.length() == 0 )
		{
			inAmount = "0";
		}
		return Double.parseDouble( inAmount );
	}
	
	public String formatNoSymbol( String inAmount )
	{
		String formatted = format( inAmount );
		return stripSymbol( formatted );
	}

	protected String stripSymbol( String formatted )
	{
		return formatted.replaceAll( getDecimalFormatSymbols().getCurrencySymbol(), "" ).trim();
	}
	
	public String formatNoSymbol( BigDecimal inAmount )
	{
		String formatted = format( inAmount );
		return stripSymbol( formatted );
	}

	protected DecimalFormatSymbols createSymbols( Currency inCurrency )
	{
		DecimalFormatSymbols symbols = new DecimalFormatSymbols( getLocale() );
		String symbol = inCurrency.getSymbol( getLocale() );
		//Special treatment for Euros
		symbol = symbol.replaceAll( "EUR", "\u20AC" );
		symbols.setCurrencySymbol( symbol );
		return symbols;
	}

	protected Currency createCurrency()
	{
		return Currency.getInstance( getCurrencyCode() );
	}

	protected static String createPattern( Locale inLocale )
	{
		DecimalFormat formatPattern =
			(DecimalFormat) NumberFormat.getCurrencyInstance( inLocale );
		return formatPattern.toPattern();
	}
	
	public Locale getLocale()
	{
		return fieldLocale;
	}

	protected void setLocale( Locale locale )
	{
		fieldLocale = locale;
	}

	public String getCurrencyCode()
	{
		return fieldCurrencyCode;
	}

	protected void setCurrencyCode( String currencyCode )
	{
		fieldCurrencyCode = currencyCode;
	}
}
