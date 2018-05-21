package com.advancedpwr.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 31, 2011
*  
*  View classes need not extend this class.  This class is a convenience for view classes
*  that need access to the {@link PageContext}.
*/
public abstract class AbstractView
{
	protected PageContext fieldPageContext;
	protected StringBuffer fieldBuffer;
	
	public AbstractView()
	{
		
	}
	public AbstractView( PageContext inContext )
	{
		setPageContext( inContext );
	}

	protected HttpServletRequest getRequest()
	{
		return (HttpServletRequest)getPageContext().getRequest();
	}
	
	public PageContext getPageContext()
	{
		return fieldPageContext;
	}

	public void setPageContext( PageContext pageContext )
	{
		fieldPageContext = pageContext;
	}

	public StringBuffer getBuffer()
	{
		if ( fieldBuffer == null )
		{
			fieldBuffer = new StringBuffer();
		}
		return fieldBuffer;
	}

	public StringBuffer append( String inString )
	{
		return getBuffer().append( inString );
	}

	public void clearBuffer()
	{
		fieldBuffer = null;
	}
	
	protected String notNull( String inString )
	{
		if ( inString == null )
		{
			return "";
		}
		return inString;
	}
	
	public String getApplicationPath()
	{
		return getRequest().getContextPath();
	}
}
