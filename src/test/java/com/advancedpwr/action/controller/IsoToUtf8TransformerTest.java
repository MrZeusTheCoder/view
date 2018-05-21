package com.advancedpwr.action.controller;

import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class IsoToUtf8TransformerTest extends TestCase
{
	public void testUnsupportedEncodingException()
	{
		IsoToUtf8Transformer transformer = new IsoToUtf8Transformer()
		{

			@Override
			protected byte[] isoBytes( String inSource ) throws UnsupportedEncodingException
			{
				throw new UnsupportedEncodingException();
			}
			
		};
		
		try
		{
			transformer.transform( "A string" );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause() instanceof UnsupportedEncodingException );
		}
	}

	
	public void testUtf8()
	{
		IsoToUtf8Transformer transformer = new IsoToUtf8Transformer();
		
		System.out.println( transformer.transform( "abc" ));
	}
}
