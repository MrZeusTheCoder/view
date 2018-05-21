package com.advancedpwr.action.controller;

import java.util.Map;

public class ArrayInput extends Input
{
	protected int fieldIndex;
	
	public ArrayInput( Map inMap, String inKey )
	{
		super( inMap, inKey );
	}

	public String getValue()
	{
		String[] array = stringArray();
		if( getIndex() > array.length - 1)
		{
			return null;
		}
		return stringArray()[getIndex()];
	}

	public int getIndex()
	{
		return fieldIndex;
	}

	public void setIndex( int index )
	{
		fieldIndex = index;
	}
	
	public boolean exists()
	{
		return getValue() != null && super.exists();
	}

}
