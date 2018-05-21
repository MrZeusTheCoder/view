package com.advancedpwr.action.controller;

import java.util.Map;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jun 24, 2010
*/
public class Input
{

	public Input( Map inMap, String inKey )
	{
		map = inMap;
		key = inKey;
	}

	public String getKey()
	{
		return key;
	}

	public String getValue()
	{
		return string();
	}
	
	public int intValue()
	{
		if ( !exists() )
		{
			return 0;
		}
		return Integer.parseInt( getValue() );
	}
	
	public long longValue()
	{
		if ( !exists() )
		{
			return 0;
		}
		return Long.parseLong( getValue() );
	}
	
	public float floatValue()
	{
		if ( !exists() )
		{
			return 0;
		}
		return Float.parseFloat( getValue() );
	}
	
	public double doubleValue()
	{
		if ( !exists() )
		{
			return 0;
		}
		return Double.parseDouble( getValue() );
	}
	
	
	public boolean booleanValue()
	{
		if ( !exists() )
		{
			return false;
		}
		return Boolean.parseBoolean( getValue() );
	}

	public boolean exists()
	{
		return existsInMap() && getValue().trim().length() > 0;
	}
	
	protected Map map;
	protected String key;
	
	
	public String[] stringArray()
	{
		String[] array = new String[]{};
		Object obj = map.get(key);
		if ( obj instanceof String[] )
		{
			array = (String[]) obj;
		}
		else if ( obj instanceof String )
		{
			array = new String[]{ (String)obj };
		}
		return array;
	}
	
	public String string()
	{
		String string = "";
    	Object obj = map.get( key );
    	if ( obj instanceof String )
    	{
    		string = (String) obj;
    	}
    	else if ( obj instanceof String[] )
    	{
    		String[] array = (String[])obj;
    		if( array.length > 0 )
    		{
    			string = array[0];
    		}
    	}
    	return string;
	}
	/**
	 * Check the supplied map to see if at least an Object exists.
	 * @return
	 */
	public boolean existsInMap()
	{
		Object obj = map.get(key);
		if ( obj == null )
		{
			return false;
		}
		if ( obj instanceof String )
		{
			String string = (String)obj;
			return stringExists( string );
		}
		if ( obj instanceof String[] )
		{
			String[] array = (String[])obj;
			if ( array.length == 0 )
			{
				return false;
			}
			for ( int i = 0; i < array.length; i++ )
			{
				if ( stringExists( array[i] ) )
				{
					return true;
				}
			}
		}
		return obj != null && ( obj instanceof String || obj instanceof String[] );
	}
	
	protected boolean stringExists( String string )
	{
		return !"null".equals( string ) && !(string.length() == 0);
	}

}
