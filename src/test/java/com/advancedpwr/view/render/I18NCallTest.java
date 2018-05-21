/**
 * 
 */
package com.advancedpwr.view.render;

import com.advancedpwr.view.examples.PriceTable;
import com.advancedpwr.view.examples.SampleView;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Jun 25, 2012
 *
 */
public class I18NCallTest extends TestCase
{
	public void testExecute_not_i18n() throws Exception
	{
		I18NCall call = new I18NCall();
		call.setSource( "$i18n.continue_button()" );
		
		PriceTable priceTable = new PriceTable();
		assertEquals( "$i18n.continue_button()", call.execute( priceTable ) );
		
	}
	
	public void testExecute() throws Exception
	{
		I18NCall call = new I18NCall();
		call.setSource( "$i18n.continue_button()" );
		
		SampleView sample = new SampleView();
		assertEquals( "Continue", call.execute( sample ) );	
	}
	
	public void testExecute_missing_property() throws Exception
	{
		I18NCall call = new I18NCall();
		call.setSource( "$i18n.back_button()" );
		
		SampleView sample = new SampleView();
		assertEquals( "$i18n.back_button()", call.execute( sample ) );
		
	}
}
