/**
 * 
 */
package com.advancedpwr.view.ui.captcha;

import java.util.Locale;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Oct 10, 2012
 *
 */
public class LocaleInput
{
	protected Locale fieldDefaultLocale;
	protected Locale fieldLocale;
	
	public Locale getDefaultLocale()
	{
		if ( fieldDefaultLocale == null )
		{
			fieldDefaultLocale = Locale.getDefault();
		}
		return fieldDefaultLocale;
	}
	
	public void setDefaultLocale( Locale defaultLocale )
	{
		fieldDefaultLocale = defaultLocale;
	}

	public void setLocale( String locale )
	{
		if ( locale == null )
		{
			return;
		}
		if ( locale.contains( "_" )  )
		{
			String[] parts = locale.split( "_" );
			fieldLocale = new Locale( parts[0], parts[1] );
			return;
		}
		if ( locale.contains( "-" )  )
		{
			String[] parts = locale.split( "-" );
			fieldLocale = new Locale( parts[0], parts[1] );
			return;
		}
		fieldLocale = new Locale( locale );
	}
	
	public Locale locale()
	{
		if ( fieldLocale == null )
		{
			fieldLocale = getDefaultLocale();
		}
		return fieldLocale;
	}
}
