/**
 * 
 */
package com.advancedpwr.action.j2ee;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import com.advancedpwr.action.controller.AbstractController;
import com.advancedpwr.view.filter.PageContextAdapter;


/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 25, 2013
 *
 */
public abstract class AbstractControllerServlet<T extends AbstractController> extends HttpServlet
{
	protected T getController( PageContext inContext ) throws ServletException, IOException
	{
		HttpSession session = inContext.getSession();
		T sessionInstance = (T) session.getAttribute( sessionKey() );
		if ( sessionInstance == null )
		{
			sessionInstance = newInstance( );
			init( sessionInstance, inContext );
			session.setAttribute( sessionKey(), sessionInstance );
		}
		sessionInstance.setPageContext( inContext );
		return sessionInstance;
	}
	
	protected void service( HttpServletRequest req, HttpServletResponse res )
			throws ServletException, IOException
	{
		PageContext context = createPageContext( req, res );
		T controller = getController( context );
		controller.doAction();
	}

	protected PageContext createPageContext( HttpServletRequest req, HttpServletResponse res ) throws UnsupportedEncodingException
	{
		req.setCharacterEncoding( "UTF-8" );
		res.setCharacterEncoding( "UTF-8" );
		PageContextAdapter context = new PageContextAdapter( req, res );
		return context;
	}
	
	protected abstract void init( T controller, PageContext inContext  ) throws ServletException, IOException;
	
	protected String sessionKey()
	{
		return prototypeClass().getName();
	}

	public Class<T> prototypeClass()
	{
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) superclass.getActualTypeArguments()[ 0 ];
	}

	protected T newInstance()
	{
		try
		{
			return prototypeClass().newInstance();
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}	
}