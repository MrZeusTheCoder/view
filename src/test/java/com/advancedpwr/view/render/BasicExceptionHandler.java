package com.advancedpwr.view.render;

import java.io.PrintStream;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 22, 2011
*/
public class BasicExceptionHandler implements ExceptionHandler
{
	
	public void handleException( PrintStream stream, Exception e )
	{
		e.printStackTrace( stream );
		if ( e instanceof RuntimeException )
		{
			throw (RuntimeException)e;
		}
		throw new RuntimeException( e );
	}

}
