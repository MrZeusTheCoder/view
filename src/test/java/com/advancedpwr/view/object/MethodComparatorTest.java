/**
 * 
 */
package com.advancedpwr.view.object;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Apr 2, 2012
 *
 */
public class MethodComparatorTest extends TestCase
{
	public void testMethodComparator()
	{
		List setters = setters( Ship.class );
		assertEquals( 6, setters.size() );
		assertTrue( setters.get(0).toString().contains( ".setContractNumber" ) );
	}
	
	protected boolean isSetter( Method method )
	{
		return method.getName().startsWith( "set" ) && method.getParameterTypes().length > 0;
	}

	protected List setters( Class inClass )
	{
		Method[] methods = inClass.getMethods();
		List list = new ArrayList();
		for ( int i = 0; i < methods.length; i++ )
		{
			Method method = methods[ i ];
			if ( isSetter( method ) )
			{
				list.add( method );
			}
		}
		Collections.sort( list, new MethodComparator() );
		return list;
	}
}
