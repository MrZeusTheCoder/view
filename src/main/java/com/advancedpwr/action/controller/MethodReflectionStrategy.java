package com.advancedpwr.action.controller;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*  
*  Calls a public method based on the input without name transformation.
*  
*/
public class MethodReflectionStrategy extends AbstractPopulationStrategy
{

	public void setValue( Object inTarget, Input input )
	{
		Method method = null;

		String methodName = methodName( input );
		method = findMethod( inTarget, methodName );			
		if ( method == null )
		{
			methodName = inputName( input );
			method = findMethod( inTarget, methodName );
		}
		if ( method == null )
		{
			return;
		}
		invokeMethod( inTarget, input, method );
	}

	protected String methodName( Input input )
	{
		return inputName( input );
	}

	protected Method findMethod( Object inTarget, String methodName )
	{
		Method[] methods = inTarget.getClass().getMethods();
		for ( int i = 0; i < methods.length; i++ )
		{				
			if ( methods[i].getName().equals( methodName ) )
			{
				return methods[i];
			}
		}
		return null;
	}

	protected void invokeMethod( Object inTarget, Input input, Method method )
	{
		if ( method.getParameterTypes().length == 0 )
		{
			dispatchMethodCall( inTarget, input, method, null );
			return;
		}
		Class parameterType = method.getParameterTypes()[0];
		dispatchMethodCall( inTarget, input, method, parameterType );
		
	}

	protected void dispatchMethodCall( Object inTarget, Input input, Method method,
			Class parameterType ) 
	{
		try
		{
			if ( parameterType == null )
			{
				method.invoke( inTarget, new Object[]{} );
				return;
			}
			if ( parameterType.equals( String.class  ) )
			{
				method.invoke( inTarget, transform( input.getValue() ) );
			}
			if ( parameterType.equals( int.class ) || parameterType.equals( Integer.class ) )
			{
				method.invoke( inTarget, new Integer( input.intValue() ) );
			}
			if ( parameterType.equals( long.class ) || parameterType.equals( Long.class ) )
			{
				method.invoke( inTarget, new Long( input.longValue() ) );
			}
			if ( parameterType.equals( boolean.class ) || parameterType.equals( Boolean.class ) )
			{
				method.invoke( inTarget, new Boolean( input.booleanValue() ) );
			}
			if ( parameterType.equals( float.class ) || parameterType.equals( Float.class ) )
			{
				method.invoke( inTarget, new Float( input.floatValue() ) );
			}
			if ( parameterType.equals( double.class ) || parameterType.equals( Double.class ) )
			{
				method.invoke( inTarget, new Double( input.doubleValue() ) );
			}
			if ( parameterType.equals( BigInteger.class )  )
			{
				method.invoke( inTarget, new BigInteger( input.getValue() ) );
			}
			if ( parameterType.equals( BigDecimal.class ) )
			{
				method.invoke( inTarget, new BigDecimal( input.getValue() ) );
			}
			
		}
		catch ( Exception e )
		{
			throw new RuntimeException(e);
		}
	}
	

}
