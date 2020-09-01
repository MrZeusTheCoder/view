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
		assertEquals(Locale.getDefault(), input.getDefaultLocale());
		input.setDefaultLocale( Locale.JAPAN );
		assertEquals(Locale.JAPAN, input.getDefaultLocale());
		assertEquals(Locale.JAPAN, input.locale());
		input.setLocale( "xx_XX" );
		Locale testLocale = input.locale();
		input.setLocale(null);
		assertEquals(testLocale, input.locale());
		
		input.setLocale( "es-us" );
		assertEquals(new Locale("es", "us"), input.locale());
		
		input.setLocale( "xyz" );
		assertEquals(new Locale("xyz"), input.locale());
	}
}
