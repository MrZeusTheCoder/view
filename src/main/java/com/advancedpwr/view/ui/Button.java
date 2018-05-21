/**
 * 
 */
package com.advancedpwr.view.ui;

import com.advancedpwr.action.controller.LiveAction;
import com.advancedpwr.view.I18nView;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jun 6, 2012
 *
 */
public abstract class Button extends I18nView
{
	protected LiveAction fieldAction;
	
	protected boolean fieldDisable;
	
	protected String fieldId;
	
	protected String fieldJavascript;

	public LiveAction getAction()
	{
		return fieldAction;
	}

	public void setAction( LiveAction action )
	{
		fieldAction = action;
	}
	
	public String name()
	{
		return getClass().getSimpleName();
	}
	
	
	public String value()
	{
		return getString( name() );
	}

	protected Class<? extends I18nView> localizerClass()
	{
		return Button.class;
	}

	public boolean isDisabled()
	{
		return fieldDisable;
	}

	public void disable()
	{
		fieldDisable = true;
	}
	
	public void enable()
	{
		fieldDisable = false;
	}
	
	public String showDisabled()
	{
		if ( isDisabled() )
		{
			return "disabled=\"disabled\"";
		}
		return "";
	}

	public String getId()
	{
		return fieldId;
	}

	public void setId( String id )
	{
		fieldId = id;
	}
	
	public String id()
	{
		if ( getId() != null )
		{
			return "id=\"" + getId() + "\"";
		}
		return "";
	}

	public String getJavascript()
	{
		if ( fieldJavascript == null )
		{
			fieldJavascript = "";
		}
		return fieldJavascript;
	}

	public void setJavascript( String javascript )
	{
		fieldJavascript = javascript;
	}
}
