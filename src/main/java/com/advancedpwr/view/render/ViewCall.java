/**
 * 
 */
package com.advancedpwr.view.render;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jun 25, 2012
 *
 */
public class ViewCall
{
	public static String I18N = "$i18n.";
	protected String fieldSource;

	public static ViewCall create( String inSource )
	{
		ViewCall call = new ViewCall();
		
		if ( inSource.startsWith( I18N ) )
		{
			call = new I18NCall();
		}
		call.setSource( inSource );
		return call;
	}
	
	public String getSource()
	{
		return fieldSource;
	}

	public void setSource( String source )
	{
		fieldSource = source;
	}
	
	public String methodName()
	{
		return getSource().substring( 6 ).replaceAll( "[(].*", "" );
	}
	
	public Object execute( Object inTarget ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		Method method = inTarget.getClass().getMethod( methodName(), (Class[]) null );

		Object result = method.invoke( inTarget, new Object[] {} );
		if ( void.class.equals( method.getReturnType() ) )
		{
			return "";
		}
		if ( result == null )
		{
			return "";
		}
		return result;
	}
}
