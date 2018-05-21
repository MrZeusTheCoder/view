/**
 * 
 */
package com.advancedpwr.view.ui.captcha;

import java.util.Locale;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Oct 10, 2012
 *
 */
public class LocaleInputTest extends TestCase
{
	public void testLocale()
	{
		LocaleInput input = new LocaleInput();
		input.setDefaultLocale( Locale.JAPAN );
		input.setLocale( "xx_XX" );
		System.out.println( input.locale() );
		
		input.setLocale( "es" );
		
		System.out.println( input.locale() );
		
		input.setLocale( "xyz" );
		
		System.out.println( input.locale() );
		
	}
}
