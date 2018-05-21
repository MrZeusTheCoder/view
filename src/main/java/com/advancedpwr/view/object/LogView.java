package com.advancedpwr.view.object;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.advancedpwr.view.render.RendererUtility;

public class LogView
{
	protected Object fieldSource;
	
	protected Set fieldSkip;

	public Set getSkip()
	{
		if ( fieldSkip == null )
		{
			fieldSkip = new HashSet();
		}

		return fieldSkip;
	}
	
	public void skipClass( Class inClass )
	{
		getSkip().add( inClass );
	}
	
	protected boolean skip( Class inClass )
	{
		return getSkip().contains( inClass );
	}
	
	public Object getSource()
	{
		return fieldSource;
	}

	public void setSource( Object source )
	{
		fieldSource = source;
	}

	public LogView( Object inSource )
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
		if ( isBlank( inObject ) )
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
			if ( isBlank( value ) )
			{
				continue;
			}
			if ( skip( returnType ) )
			{
				continue;
			}
			if ( String.class.equals( returnType ) 
			  || returnType.isPrimitive() 
			  || Number.class.isAssignableFrom( returnType ) 
			  || Boolean.class.equals( returnType ) )
			{
				OutputTag tag = new OutputTag();
				tag.setMethod( method );
				tag.setValue( value );
				list.add( tag );
			}
			else if ( Class.class.equals( returnType ) )
			{
				OutputTag tag = new OutputTag();
				tag.setMethod( method );
				tag.setValue( ( (Class) value ).getName() );
				list.add( tag );
			}
			else if ( returnType.isArray() )
			{
				Class arrayClass = returnType.getComponentType();
				if ( arrayClass.isPrimitive() )
				{
				
				}
				else
				{
					Object[] array = (Object[]) value;
					for ( int i = 0; i < array.length; i++ )
					{
						list.add( arrayClass.getSimpleName() + "[" + i + "]" + ":" );
						list.addAll( createOutputs( array[ i ] ) );
					}
				}
			}
			else if ( Date.class.equals( returnType ) )
			{
				OutputTag tag = new OutputTag();
				tag.setMethod( method );
				Date date = (Date) value;
				SimpleDateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy" );
				tag.setValue( formatter.format( date ) );
				list.add( tag );
			}
			else if ( Collection.class.isAssignableFrom( returnType ) )
			{
				Collection collection = (Collection) value;
				for ( Iterator iterator2 = collection.iterator(); iterator2.hasNext(); )
				{
					Object object = (Object) iterator2.next();
					list.add( object.getClass().getSimpleName() + ":" );
					list.addAll( createOutputs( object ) );	
				}
			}
			else
			{
				list.add( returnType.getSimpleName() + ":" );
				list.addAll( createOutputs( value ) );
			}

		}
		return list;
	}

	protected boolean isBlank( Object value )
	{
		return value == null;
	}

	protected Object invokeMethod( Object inObject, Method method )
	{
		try
		{
			Object value = method.invoke( inObject, new Object[] {} );
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
		return Modifier.PUBLIC == method.getModifiers() && ( name.startsWith( "get" ) || name.startsWith( "is" ) ) && !name.equals( "getClass" );
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

	public String toString()
	{
		RendererUtility util = new RendererUtility();
		return util.render( this, ".txt" );
	}

}
