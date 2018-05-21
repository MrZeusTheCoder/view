package com.advancedpwr.view.object;

import java.lang.reflect.Method;
import java.util.Comparator;

public class MethodComparator implements Comparator<Method>
{

	public int compare( Method m1, Method m2 )
	{
		if ( m1 == null )
		{
			return -1;
		}
		if ( m2 == null )
		{
			return 1;
		}
		boolean p1 = isPrimitive( m1 );
		boolean p2 = isPrimitive( m2 );
		if ( p1 && !p2 )
		{
			return -1;
		}
		if ( !p1 && p2 )
		{
			return 1;
		}
		return m1.getName().compareTo( m2.getName() );
	}

	protected boolean isPrimitive( Method method )
	{
		Class[] types = method.getParameterTypes();
		if ( types.length == 0 )
		{
			return false;
		}
		Class param0 = types[ 0 ];
		return String.class.equals( param0 ) || param0.isPrimitive();
	}

}
