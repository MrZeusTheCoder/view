package com.advancedpwr.view.tags;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 10, 2011
*/
public class HiddenActionInput
{
	protected Object fieldValue;
	
	public HiddenActionInput( Object inValue )
	{
		setValue( inValue );
	}

	public Object value()
	{
		return fieldValue;
	}

	public void setValue( Object action )
	{
		fieldValue = action;
	}
}
