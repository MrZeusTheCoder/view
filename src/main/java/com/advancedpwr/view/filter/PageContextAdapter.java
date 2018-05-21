package com.advancedpwr.view.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.el.ELContext;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.ErrorData;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 13, 2011
*/
public class PageContextAdapter extends PageContext
{

	protected HttpServletRequest fieldRequest;
	protected HttpServletResponse fieldResponse;
	
	public PageContextAdapter( HttpServletRequest inRequest, HttpServletResponse inResponse )
	{
		fieldRequest = inRequest;
		fieldResponse = inResponse;
	}
	
	
	public void forward( String s ) throws ServletException, IOException
	{
		fieldRequest.getRequestDispatcher( s ).forward( fieldRequest, fieldResponse );
	}

	
	public Exception getException()
	{
		return null;
	}

	
	public Object getPage()
	{
		return null;
	}

	
	public HttpServletRequest getRequest()
	{
		return fieldRequest;
	}

	
	public ServletResponse getResponse()
	{
		return fieldResponse;
	}

	
	public ServletConfig getServletConfig()
	{
		return null;
	}

	
	public ServletContext getServletContext()
	{
		return null;
	}

	
	public HttpSession getSession()
	{
		return fieldRequest.getSession();
	}

	
	public void handlePageException( Exception exception ) throws ServletException, IOException
	{
	}

	
	public void handlePageException( Throwable throwable ) throws ServletException, IOException
	{
	}

	
	public void include( String s ) throws ServletException, IOException
	{
	}

	
	public void include( String s, boolean flag ) throws ServletException, IOException
	{
	}

	
	public void initialize( Servlet servlet, ServletRequest servletrequest,
			ServletResponse servletresponse, String s, boolean flag, int i, boolean flag1 )
			throws IOException, IllegalStateException, IllegalArgumentException
	{
	}

	
	public void release()
	{
	}

	
	public Object findAttribute( String s )
	{
		return null;
	}

	
	public Object getAttribute( String s )
	{
		return null;
	}

	
	public Object getAttribute( String s, int i )
	{
		return null;
	}

	
	public Enumeration getAttributeNamesInScope( int i )
	{
		return null;
	}

	
	public int getAttributesScope( String s )
	{
		return 0;
	}

	
	public JspWriter getOut()
	{
		return null;
	}

	
	public void removeAttribute( String s )
	{
	}

	
	public void removeAttribute( String s, int i )
	{
	}

	
	public void setAttribute( String s, Object obj )
	{
	}

	
	public void setAttribute( String s, Object obj, int i )
	{
	}


	/* (non-Javadoc)
	 * @see javax.servlet.jsp.PageContext#getAttributeNames()
	 */
	public Enumeration getAttributeNames()
	{
		return null;
	}


	/* (non-Javadoc)
	 * @see javax.servlet.jsp.PageContext#getErrorData()
	 */
	public ErrorData getErrorData()
	{
		return null;
	}




	public ELContext getELContext()
	{
		return null;
	}


	public ExpressionEvaluator getExpressionEvaluator()
	{
		return null;
	}


	public VariableResolver getVariableResolver()
	{
		return null;
	}





}
