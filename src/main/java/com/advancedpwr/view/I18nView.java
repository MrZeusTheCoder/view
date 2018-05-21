package com.advancedpwr.view;

import java.math.BigDecimal;
import java.util.Locale;

import javax.servlet.jsp.PageContext;

import com.advancedpwr.action.i18n.CurrencyFormat;
import com.advancedpwr.action.i18n.Localizer;
import com.advancedpwr.view.render.I18nProvider;

public class I18nView extends AbstractView implements I18nProvider
{

	public I18nView()
	{
		super();
	}

	public I18nView( PageContext inContext )
	{
		super( inContext );
	}

	protected Localizer fieldLocalizer;

	public Locale getLocale()
	{
		return getLocalizer().getLocale();
	}

	public Localizer getLocalizer()
	{
		if ( fieldLocalizer == null )
		{
			fieldLocalizer = createLocalizer();
		}
		return fieldLocalizer;
	}

	protected Localizer createLocalizer()
	{
		final String name = localizerClass().getName();
		return new Localizer()
		{
			public String getBaseName()
			{
				return name;
			}
		};
	}

	protected Class<? extends I18nView> localizerClass()
	{
		return getClass();
	}

	public void setLocalizer( Localizer localizer )
	{
		fieldLocalizer = localizer;
	}

	public String getString( String inKey )
	{
		return getLocalizer().getString( inKey ).trim();
	}

	public void setLocale( Locale inLocale )
	{
		getLocalizer().setLocale( inLocale );
	}
	
	public String formatCurrency( BigDecimal amount )
	{
		return getCurrencyFormat().toHtml(  amount );
	}
	
	public CurrencyFormat getCurrencyFormat()
	{
		return getLocalizer().getCurrencyFormat();
	}
}
