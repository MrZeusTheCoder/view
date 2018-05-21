package com.advancedpwr.action.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLUtility
{
	private static final String UTF_8 = "UTF-8";

	public String encode( String inString )
	{
		try
		{
			return encodeRaw( inString );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw new RuntimeException(e);
		}
	}

	protected String encodeRaw( String inString ) throws UnsupportedEncodingException
	{
		return URLEncoder.encode( inString, UTF_8 );
	}
	
	public String decode( String inString )
	{
		try
		{
			return decodeRaw( inString );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw new RuntimeException(e);
		}
	}

	protected String decodeRaw( String inString ) throws UnsupportedEncodingException
	{
		return URLDecoder.decode( inString, UTF_8 );
	}
}
