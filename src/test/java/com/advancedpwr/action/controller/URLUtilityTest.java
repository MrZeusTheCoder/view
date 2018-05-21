package com.advancedpwr.action.controller;

import java.io.UnsupportedEncodingException;

import junit.framework.TestCase;

public class URLUtilityTest extends TestCase
{
	public void testEncode()
	{
		URLUtility util = new URLUtility();
		String encoded = util.encode( "5+3=" );
		assertEquals( "5%2B3%3D", encoded );
	}
	
	public void testEncode_exception()
	{
		URLUtility util = new URLUtility()
		{
			protected String encodeRaw(String inString) throws UnsupportedEncodingException 
			{
				throw new UnsupportedEncodingException();
			};
		};
		try
		{
			util.encode( "5+3=" );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause() instanceof UnsupportedEncodingException );
		}
	}
	
	public void testDecode()
	{
		URLUtility util = new URLUtility();
		String decoded = util.decode( "5%2B3%3D" );
		assertEquals( "5+3=", decoded );
	}
	
	public void testDecode_exception()
	{
		URLUtility util = new URLUtility()
		{
			protected String decodeRaw(String inString) throws UnsupportedEncodingException 
			{
				throw new UnsupportedEncodingException();
			};
		};
		try
		{
			util.decode( "5%2B3%3D" );;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause() instanceof UnsupportedEncodingException );
		}
	}
	
}
