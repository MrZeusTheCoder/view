package com.advancedpwr.action.controller;

import java.lang.reflect.Field;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 2, 2011
*/
public class PublicFieldReflectionStrategy extends AbstractPopulationStrategy
{
	/* (non-Javadoc)
	 * @see com.siemens.marketplace.controller.PopulationStrategy#setValue(java.lang.Object, com.siemens.marketplace.controller.Input)
	 */
	public void setValue( Object inTarget, Input input )
	{

		Field field = null;
		try
		{
			field = inTarget.getClass().getField( inputName( input ) );
		}
		catch ( NoSuchFieldException e1 )
		{
			return;
		}
		try
		{
			setField( inTarget, input, field );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

	protected void setField( Object inTarget, Input input, Field field ) throws IllegalAccessException
	{
		if ( String.class.equals( field.getType() ) )
		{
			field.set( inTarget, transform( input.string() ) );
		}
		else if( int.class.equals( field.getType() ) )
		{
			field.set( inTarget, input.intValue() );
		}
	}
}
