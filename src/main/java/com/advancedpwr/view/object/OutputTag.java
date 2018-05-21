package com.advancedpwr.view.object;

public class OutputTag extends InputTag
{
	protected Object fieldValue;
	
	public String disabled()
	{
		return "";
	}
	
	public String name()
	{
		return getMethod().getName().replaceFirst( "get", "" );
	}
	
	public Object value() throws Exception
	{
		return getValue();
	}

	public Object getValue()
	{
		return fieldValue;
	}

	public void setValue( Object value )
	{
		fieldValue = value;
	}

}
