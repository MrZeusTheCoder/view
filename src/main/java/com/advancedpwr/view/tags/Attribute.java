package com.advancedpwr.view.tags;

public class Attribute
{
	protected String fieldName;
	protected String fieldValue;
	public String getName()
	{
		return fieldName;
	}
	public void setName( String name )
	{
		fieldName = name;
	}
	public String getValue()
	{
		return fieldValue;
	}
	public void setValue( String value )
	{
		fieldValue = value;
	}
	
	public String toString()
	{
		if ( getValue() == null )
		{
			return "";
		}
		return getName() + "=\"" + getValue() + "\"";
	}
}
