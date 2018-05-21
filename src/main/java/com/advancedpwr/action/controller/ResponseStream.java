package com.advancedpwr.action.controller;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletResponse;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 15, 2011
*/
public class ResponseStream extends PrintStream
{

	public ResponseStream( ServletResponse response ) throws IOException
	{
		super( response.getOutputStream(), true, response.getCharacterEncoding() );
	}


}
