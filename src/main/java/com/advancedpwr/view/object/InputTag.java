package com.advancedpwr.view.object;

import java.lang.reflect.Method;

public class InputTag
{
	protected Method fieldMethod;
	
	protected String fieldNamespace;
	
	public String name()
	{
		return getMethod().getName().replaceFirst( "set", "" );
	}
	
	public String inputName()
	{
		return namespace() + getMethod().getName().replaceFirst( "set", "" );
	}

	public Method getMethod()
	{
		return fieldMethod;
	}

	public void setMethod( Method method )
	{
		fieldMethod = method;
	}

	protected String namespace()
	{
		if ( getNamespace() == null )
		{
			return "";
		}
		return getNamespace() + ".";
	}
	public String disabled()
	{
		return "";
	}
	
	public Object value() throws Exception
	{
		return "";
	}

	public String getNamespace()
	{
		return fieldNamespace;
	}

	public void setNamespace( String namespace )
	{
		fieldNamespace = namespace;
	}
}
