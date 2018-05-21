package com.advancedpwr.action.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 14, 2011
*/
public class MethodAction extends LiveAction
{
	private static final Logger LOGGER = LoggerFactory.getLogger( MethodAction.class );
	
	protected Object fieldTarget;
	protected Action fieldAction;
	
	public MethodAction( Object inTarget, Action inAction )
	{
		fieldTarget = inTarget;
		fieldAction = inAction;
	}
	
	@Override
	public void execute()
	{
		if ( !hasMethod() )
		{
			warn( id() + " method not found! skipping action..." );
			return;
		}
		try
		{
			invokeMethod();
		}
		catch( Exception e )
		{
			error( "Error attempting to exectute MethodAction " + id(), e );
		}
	}

	protected void invokeMethod() throws Exception
	{
		LOGGER.debug( "Executing action: " + id() );
		Method method = method();
		method.invoke( getTarget(), new Object[]{} );
	}
	
	protected void warn( String message )
	{
		LOGGER.warn( message );
	}
	
	protected void error( String message, Throwable t )
	{
		if ( t instanceof InvocationTargetException )
		{
			t = t.getCause();
		}
		LOGGER.error( message, t );
		if ( t instanceof RuntimeException )
		{
			throw (RuntimeException)t;
		}
		throw new RuntimeException( message, t );
	}
	
	protected String id()
	{
		return targetClass().getSimpleName() + "." + getMethodName() + "()";
	}

	@Override
	public String key()
	{
		return getMethodName();
	}

	protected boolean hasMethod()
	{
		try
		{
			method();
		}
		catch ( NoSuchMethodException e )
		{
			return false;
		}
		return true;
	}
	
	protected Method method() throws NoSuchMethodException
	{
		return targetClass().getMethod( getMethodName(), new Class[]{} );	
	}
	
	protected Class targetClass()
	{
		return getTarget().getClass();
	}
	
	protected Object getTarget()
	{
		return fieldTarget;
	}

	protected String getMethodName()
	{
		return getAction().getActionMethod();
	}

	protected Action getAction()
	{
		return fieldAction;
	}

	public boolean hasAction()
	{
		return getAction().hasAction() && hasMethod();
	}

}
