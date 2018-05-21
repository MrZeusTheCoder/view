package com.advancedpwr.view.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.advancedpwr.action.controller.ActionPerformer;

public abstract class ActionFilter<T extends ActionPerformer> implements Filter
{

	public void destroy()
	{
	}

	public void doFilter( ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain )
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)servletrequest;
		HttpServletResponse res = (HttpServletResponse) servletresponse;
		PageContext context = createPageContext( req, res );
		ActionPerformer controller = getController( context );
		if ( !controller.doAction() )
		{
			filterchain.doFilter( servletrequest, servletresponse );	
		}
		
	}

	protected abstract T getController( PageContext inContext ) throws ServletException;
	
	
	public void init( FilterConfig arg0 ) throws ServletException
	{
	}
	
	protected PageContext createPageContext( HttpServletRequest req, HttpServletResponse res ) throws UnsupportedEncodingException
	{
		req.setCharacterEncoding( "UTF-8" );
		res.setCharacterEncoding( "UTF-8" );
		PageContextAdapter context = new PageContextAdapter( req, res );
		return context;
	}

}
