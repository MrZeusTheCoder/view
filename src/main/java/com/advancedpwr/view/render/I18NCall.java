/**
 * 
 */
package com.advancedpwr.view.render;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.MissingResourceException;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jun 25, 2012
 *
 */
public class I18NCall extends ViewCall
{

	public Object execute( Object inTarget ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException 
	{
		if ( is18n( inTarget ) )
		{
			Object result = getString( inTarget );
			if ( result != null )
			{
				return result;
			}
		}
		return getSource();
	}

	protected Object getString( Object inTarget ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		try
		{
			Method method =inTarget.getClass().getMethod( "getString", new Class[]{ String.class } );
			return method.invoke( inTarget, new Object[] { methodName() } );
		}
		catch ( InvocationTargetException ite )
		{
			if ( ite.getTargetException() instanceof MissingResourceException )
			{
				throw new NoSuchMethodException( methodName() );
			}
			throw ite;
		}
	}

	protected boolean is18n( Object inTarget )
	{
		return I18nProvider.class.isAssignableFrom( inTarget.getClass() );
	}
}
