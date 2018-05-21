package com.advancedpwr.action.controller;

import java.lang.reflect.Field;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*/
public class FieldMapTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testFieldValueException() throws Exception
	{
		FieldMap fieldMap = new FieldMap()
		{

			@Override
			protected Object fieldValueRaw( Object inObject, Field field )
					throws IllegalAccessException
			{
				throw new IllegalAccessException();
			}
			
		};
		
		GenericTestObject obj = new GenericTestObject();
		
		try
		{
			fieldMap.fieldValue( obj, obj.getClass().getField( "lastName" ) );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause() instanceof IllegalAccessException );
		}
	}
}
