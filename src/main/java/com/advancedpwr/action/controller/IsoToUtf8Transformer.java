package com.advancedpwr.action.controller;

import java.io.UnsupportedEncodingException;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 25, 2011
*/
public class IsoToUtf8Transformer implements StringTransformer
{
	private static final String UTF8 = "UTF8";
	private static final String ISO_8859_1 = "ISO-8859-1";
	
	public String transform( String inSource )
	{
		try
		{
			byte[] isoBytes = isoBytes( inSource );
			return new String( isoBytes, UTF8 );
		}
		catch ( UnsupportedEncodingException e )
		{
			throw new RuntimeException( e );
		}
	}

	protected byte[] isoBytes( String inSource ) throws UnsupportedEncodingException
	{
		return inSource.getBytes( ISO_8859_1 );
	}

}
