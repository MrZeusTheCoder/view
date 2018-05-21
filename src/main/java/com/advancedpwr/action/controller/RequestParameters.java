package com.advancedpwr.action.controller;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Aug 3, 2010
*
*  Convert a POJO into a request parameter string
*/
public class RequestParameters
{
	protected Set fieldObjects = new LinkedHashSet();
	protected StringBuffer fieldBuffer;
	protected boolean first = true;
	
	protected StringBuffer getBuffer()
	{
		if ( fieldBuffer == null )
		{
			fieldBuffer = new StringBuffer();
		}
		return fieldBuffer;
	}
	
	protected void clearBuffer()
	{
		fieldBuffer = null;
		first = true;
	}
	
	protected void append( String inString )
	{
		getBuffer().append( inString );
	}
	
	public String parameters()
	{
		clearBuffer();
		FieldMap fields = new FieldMap();
		for ( Iterator iterator = getObjects().iterator(); iterator.hasNext(); )
		{
			fields.addObject( iterator.next() );	
		}
		appendFields( fields );
		return getBuffer().toString();
	}

	public void appendFields( Map inFields )
	{
		for ( Iterator iterator = inFields.entrySet().iterator(); iterator.hasNext(); )
		{
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
			Object result = entry.getValue();
			if ( result != null )
			{
				append( prefix() );
				append( entry.getKey() );
				append( "=" );
				append( encode( result ) );
			}
		}
	}

	protected String encode( Object result )
	{
		return new URLUtility().encode( result.toString() );
	}



	protected String prefix()
	{
		if ( first )
		{
			first = false;
			return "?";
		}
		return "&";
	}
	public String toString()
	{
		if ( getObjects().isEmpty() )
		{
			return "";
		}
		return parameters();
	}

	public void addObject( Object object )
	{
		getObjects().add( object );
	}

	protected Set getObjects()
	{
		return fieldObjects;
	}


}
