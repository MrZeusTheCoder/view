package com.advancedpwr.view.examples;

import com.advancedpwr.view.tags.HiddenActionInput;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 10, 2011
*/
public class TestHiddenActionInput extends HiddenActionInput
{
	
	public TestHiddenActionInput( Object inValue )
	{
		super( inValue );
	}
	
	public String toString()
	{
		return getClass().getName();
	}
}
