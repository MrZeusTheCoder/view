package com.advancedpwr.view.ui;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.advancedpwr.action.controller.Input;
import com.advancedpwr.view.I18nView;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Sep 7, 2010
*/
public abstract class AbstractSelector<T extends Option> extends I18nView
{

	protected List<T> fieldOptions;
	protected T fieldNotSelected;
	protected String fieldCssClass;
	protected String fieldTitle;
	
	protected String fieldOnChange;
	
	protected boolean fieldDisabled;
	
	public List<T> getOptions()
	{
		if ( fieldOptions == null )
		{
			fieldOptions = loadOptions();
		}
		return fieldOptions;
	}

	protected abstract List<T> loadOptions();


	public T getNotSelected()
	{
		if ( fieldNotSelected == null )
		{
			fieldNotSelected = createNotSelectedOption();
		}
		return fieldNotSelected;
	}

	protected T createNotSelectedOption()
	{
		return (T)new Option().setText( "Please select" ).setValue( "" );
	}

	public Option select( String inValue )
	{
		Option selected = null;
		for ( Iterator iterator = getOptions().iterator(); iterator.hasNext(); )
		{
			Option option = (Option) iterator.next();
			if ( option.getValue().equals( inValue ) )
			{
				selected = option;
				selected.setSelected( true );
			}
			else
			{
				option.setSelected( false );
			}
		}
		if ( selected == null )
		{
			Option blank = getNotSelected();
			blank.setSelected( true );
			selected = blank;
		}
		return selected;
	}
	
	public T selected()
	{
		for ( Iterator iterator = getOptions().iterator(); iterator.hasNext(); )
		{
			Option option = (Option) iterator.next();
			if( option.isSelected() )
			{
				return (T)option;
			}
		}
		return getNotSelected();
	}

	protected abstract String key();

	public String name()
	{
		return key();
	}
	
	public String id()
	{
		return key();
	}

	public String getCssClass()
	{
		return fieldCssClass;
	}

	public void setCssClass( String cssClass )
	{
		fieldCssClass = cssClass;
	}

	public String getTitle()
	{
		return fieldTitle;
	}

	public void setTitle( String title )
	{
		fieldTitle = title;
	}
	
	public String cssClass()
	{
		if ( getCssClass() == null )
		{
			return "";
		}
		return "class=\"" + getCssClass() + "\"";
	}
	
	public String title()
	{
		if( getTitle() == null )
		{
			return "";
		}
		
		return "title=\"" + getTitle() + "\"";
	}

	public boolean isDisabled()
	{
		return fieldDisabled;
	}

	public void setDisabled( boolean disabled )
	{
		fieldDisabled = disabled;
	}
	
	public String disabled()
	{
		if ( isDisabled() )
		{
			return "disabled=\"disabled\"";
		}
		return "";
	}

	public String getOnChange()
	{
		return fieldOnChange;
	}

	public void setOnChange( String onChange )
	{
		fieldOnChange = onChange;
	}
	
	public String onChange()
	{
		if ( hasOnChange() )
		{
			return "onchange=\"" + getOnChange()
					+ "\"";
		}
		return "";
	}

	protected boolean hasOnChange()
	{
		return getOnChange() != null;
	}
	
	protected Option createOption( String value )
	{
		return new Option().setText( value ).setValue( value );
	}
	
	public void scrape( Map inMap )
	{
		Input input = new Input(inMap, key() );
		if ( input.exists() )
		{
			select( input.string() );
		}
		else
		{
			getNotSelected().setSelected( true );
		}
	}
}
