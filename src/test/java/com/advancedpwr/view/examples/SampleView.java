package com.advancedpwr.view.examples;

import java.io.IOException;

import com.advancedpwr.view.render.I18nProvider;
import com.advancedpwr.view.tags.HiddenActionInput;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 26, 2011
*/
public class SampleView implements I18nProvider
{
	public I18nResource i18n = new I18nResource();
	
	public String proposalSummaryView()
	{
		return "include proposal summary view here";
	}
	
	public void aVoidMethod() throws IOException
	{
		
	}
	
	public String aNullMethod()
	{
		return null;
	}
	
	public PriceTable priceSummaryView()
	{
		return new PriceTable();
	}
	
	public HiddenActionInput startAction()
	{
		HiddenActionInput start = new TestHiddenActionInput( "start" );
		return start;
	}

//	public String continue_button()
//	{
//		return i18n.continue_button();
//	}

	/* (non-Javadoc)
	 * @see com.advancedpwr.view.render.I18nProvider#getString(java.lang.String)
	 */
	public String getString( String inKey )
	{
		if ( "continue_button".equals( inKey ) )
		{
			return "Continue";
		}
		return null;
	}
	

}
