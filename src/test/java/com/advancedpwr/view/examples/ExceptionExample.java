package com.advancedpwr.view.examples;

import java.io.IOException;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 28, 2011
*/
public class ExceptionExample extends SampleView
{

	@Override
	public void aVoidMethod() throws IOException
	{
		throw new IOException( "Bad stuff!" );
	}

}
