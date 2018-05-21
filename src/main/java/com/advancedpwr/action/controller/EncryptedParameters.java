package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.advancedpwr.action.config.Crypt;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Oct 22, 2010
*/
public class EncryptedParameters extends Input
{
	public static final String KEY = "c";
	
	public EncryptedParameters( Map inMap )
	{
		super( inMap, KEY );
	}

	public Map getParameters()
	{
		Map map = new HashMap();
		String parameters = new String( decode( getValue() ) );
		StringTokenizer st = new StringTokenizer( parameters, "&" );
		while ( st.hasMoreTokens() )
		{
			String token = st.nextToken();
			String[] keyValue = token.split( "=" );
			map.put( keyValue[0], urlDecode( keyValue[1] ) );
		}
		return map;
	}
	
	protected String urlDecode( String string )
	{
		return new URLUtility().decode( string );
	}

	protected String decode( String encrypted )
	{
		Crypt e = new Crypt();
		return e.decrypt( encrypted );
	}
	
	public static String encrypt( String parameters )
	{
		Crypt e = new Crypt();
		return KEY + "=" + e.encrypt( parameters );
	}
	
}
