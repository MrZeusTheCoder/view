package com.advancedpwr.view.exception;

import java.io.PrintStream;

import com.advancedpwr.view.render.ResourceViewRenderer;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 22, 2011
*/
public class ExceptionView
{
	protected Throwable fieldException;
	protected PrintStream fieldPrintStream;
	protected ExceptionHeader fieldHeader;
	protected Object fieldFooter;
	public Throwable getException()
	{
		return fieldException;
	}
	public void setException( Throwable exception )
	{
		fieldException = exception;
	}
	public PrintStream getPrintStream()
	{
		return fieldPrintStream;
	}
	public void setPrintStream( PrintStream printStream )
	{
		fieldPrintStream = printStream;
	}

	public void render()
	{
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setExtension( ".html" );
//		renderer.setExceptionHandler( new  );
		renderer.setPrintStream( getPrintStream() );
		getHeader().setException( getException() );
		renderer.setView( getHeader() );
		renderer.render();
		getException().printStackTrace( getPrintStream() );
		renderer.setView( getFooter() );
		renderer.render();
	}
	public ExceptionHeader getHeader()
	{
		if ( fieldHeader == null )
		{
			fieldHeader = new ExceptionHeader();
		}
		return fieldHeader;
	}
	public void setHeader( ExceptionHeader header )
	{
		fieldHeader = header;
	}
	public Object getFooter()
	{
		if ( fieldFooter == null )
		{
			fieldFooter = new ExceptionFooter();
		}
		return fieldFooter;
	}
	public void setFooter( Object footer )
	{
		fieldFooter = footer;
	}
}
