package com.advancedpwr.view.table;

public class ClassAttribute
{
	protected String fieldCssClass;
	
	public Object cssClass()
	{
		if ( getCssClass() == null )
		{
			return "";
		}
		return "class=\"" + getCssClass() + "\"";
	}

	public String getCssClass()
	{
		return fieldCssClass;
	}

	public void setCssClass( String cssClass )
	{
		fieldCssClass = cssClass;
	}
}
