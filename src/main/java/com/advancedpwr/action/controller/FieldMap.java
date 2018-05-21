package com.advancedpwr.action.controller;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 16, 2011
*/
public class FieldMap extends LinkedHashMap<String, Object>
{
	public void addObject( Object inObject )
	{
		Field[] fields = inObject.getClass().getFields();
		for ( int i = 0; i < fields.length; i++ )
		{
			Field field = fields[i];
			Object value = fieldValue( inObject, field );
			put( field.getName(), value );
		}
	}

	protected Object fieldValue( Object inObject, Field field )
	{
		try
		{
			return fieldValueRaw( inObject, field );
		}
		catch ( IllegalAccessException e )
		{
			throw new RuntimeException( e );
		}
	
	}

	protected Object fieldValueRaw( Object inObject, Field field ) throws IllegalAccessException
	{
		return field.get( inObject );
	}
	
	
}
