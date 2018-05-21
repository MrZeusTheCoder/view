package com.advancedpwr.view.object;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ObjectView
{
	protected Object fieldSource;

	public Object getSource()
	{
		return fieldSource;
	}

	public void setSource( Object source )
	{
		fieldSource = source;
	}
	
	public ObjectView( Object inSource )
	{
		setSource( inSource );
	}
	
	public List render() throws Exception
	{

		List list = createOutputs( getSource() );

		return list;
	}

	protected List createOutputs( Object inObject )
	{
		if ( inObject == null )
		{
			return new ArrayList();
		}
		List getters = getters( inObject.getClass() );

		List list = new ArrayList();
		for ( Iterator iterator = getters.iterator(); iterator.hasNext(); )
		{
			Method method = (Method) iterator.next();
			Class returnType = method.getReturnType();
			Object value = invokeMethod( inObject, method );
			if ( value == null )
			{
				continue;
			}
			if ( String.class.equals( returnType ) || returnType.isPrimitive() )
			{
				OutputTag tag = new OutputTag();
				tag.setMethod( method );
				tag.setValue(value);
				list.add( tag );
			}
			else if ( returnType.isArray() )
			{
				Class arrayClass = returnType.getComponentType();
				Object[] array = (Object[])value;
				for ( int i = 0; i < array.length; i++ )
				{
					list.add( "<div class=\"object\">" + arrayClass.getSimpleName() );
					list.addAll( createOutputs( array[i] ) );
					list.add( "</div>" );					
				}
			}
			else if ( Date.class.equals( returnType ) )
			{
				OutputTag tag = new OutputTag();
				tag.setMethod( method );
				Date date = (Date)value;
				SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
				tag.setValue(formatter.format( date ) );
				list.add( tag );
			}
			else
			{
				list.add( "<div class=\"object\">" + returnType.getSimpleName() );
				list.addAll( createOutputs( value ) );
				list.add( "</div>" );
			}

		}
		return list;
	}

	protected Object invokeMethod( Object inObject, Method method )
	{
		try
		{
			Object value = method.invoke( inObject, new Object[]{} );
			return value;
		}
		catch ( Exception e )
		{
			return null;
		}
	}

	protected boolean isGetter( Method method )
	{
		String name = method.getName();
		return (name.startsWith( "get" ) 
			|| name.startsWith( "is" )) 
			&& !name.equals( "getClass" );
	}

	protected List getters( Class inClass )
	{
		Method[] methods = inClass.getMethods();
		List list = new ArrayList();
		for ( int i = 0; i < methods.length; i++ )
		{
			Method method = methods[ i ];
			if ( isGetter( method ) )
			{
				list.add( method );
			}
		}
		Collections.sort( list, new MethodComparator() );
		return list;
	}
	
	
}
