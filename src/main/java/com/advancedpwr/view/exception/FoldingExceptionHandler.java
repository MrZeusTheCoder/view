package com.advancedpwr.view.exception;

import java.io.PrintStream;

import com.advancedpwr.view.render.ExceptionHandler;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 22, 2011
*/
public class FoldingExceptionHandler implements ExceptionHandler
{
	protected ExceptionView fieldExceptionView;
	
	public void handleException( PrintStream stream, Exception e )
	{
		getExceptionView().setException( e );
		getExceptionView().setPrintStream( stream );
		getExceptionView().render();
	}

	public ExceptionView getExceptionView()
	{
		if ( fieldExceptionView == null )
		{
			fieldExceptionView = new ExceptionView();
		}
		return fieldExceptionView;
	}

	public void setExceptionView( ExceptionView exceptionView )
	{
		fieldExceptionView = exceptionView;
	}

}
