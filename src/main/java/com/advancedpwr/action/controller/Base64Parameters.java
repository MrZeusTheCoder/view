package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.bind.DatatypeConverter;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Oct 22, 2010
*/
public class Base64Parameters extends Input
{
	
	public Base64Parameters( Map inMap )
	{
		super( inMap, "k" );
	}

	public Map getParameters()
	{
		Map map = new HashMap();
		String parameters = new String( DatatypeConverter.parseBase64Binary( getValue() ) );
		StringTokenizer st = new StringTokenizer( parameters, "&" );
		while ( st.hasMoreTokens() )
		{
			String token = st.nextToken();
			String[] keyValue = token.split( "=" );
			map.put( keyValue[0], keyValue[1] );
		}
		return map;
	}
	
	
}
