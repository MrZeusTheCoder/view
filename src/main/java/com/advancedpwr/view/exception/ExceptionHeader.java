package com.advancedpwr.view.exception;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 22, 2011
*/
public class ExceptionHeader
{
	protected Throwable fieldException;

	public Throwable getException()
	{
		return fieldException;
	}

	public void setException( Throwable exception )
	{
		fieldException = exception;
	}
	
	public String exceptionMessage()
	{
		return getException().getMessage();
	}
}
