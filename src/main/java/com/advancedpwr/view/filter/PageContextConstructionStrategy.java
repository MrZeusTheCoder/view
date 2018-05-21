package com.advancedpwr.view.filter;

import java.lang.reflect.Constructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

public class PageContextConstructionStrategy implements ConstructionStrategy
{

	public boolean matches( Constructor inConstructor )
	{
		Class[] params = inConstructor.getParameterTypes();
		return params.length == 1 && params[ 0 ].equals( PageContext.class );
	}

	public Object createInstance( Constructor inConstructor, HttpServletRequest inRequest,
			HttpServletResponse inResponse )
	{
		PageContextAdapter pc = new PageContextAdapter( inRequest, inResponse );
		try
		{
			return inConstructor.newInstance( pc );
		}
		catch ( Exception e )
		{
			throw new ObjectConstructionException(e);
		}
	}

}
