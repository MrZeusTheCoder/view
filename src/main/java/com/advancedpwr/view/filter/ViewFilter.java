package com.advancedpwr.view.filter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.advancedpwr.action.controller.ResponseStream;
import com.advancedpwr.action.controller.Scraper;
import com.advancedpwr.view.render.ResourceViewRenderer;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 1, 2011
*  
*  Add the following xml to web.xml to enable the ViewFilter:
*  
  	<filter>
		<filter-name>ViewFilter</filter-name>
		<filter-class>com.advancedpwr.view.filter.ViewFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>ViewFilter</filter-name>
		<url-pattern>*</url-pattern>
	</filter-mapping>

*
*	The ViewFilter will load a resource file from the classpath and interpret it
*   using the {@link ResourceViewRenderer}.  The extension for the resource is taken
*   from the URL supplied to the filter, e.g.
*   
*   http://localhost:8080/Root/com.siemens.ajax.countryregion.ListRegionsAjax.js
*   
*   will attempt to load and interpret ListRegionsAjax.js from the classpath.
*   
*   Note that the ViewFilter ignores both the context path and servlet path and
*   evaluates only that part of the URL remaining after final "/" character.
*   This means that 
*   
*   http://localhost:8080/Root/com.siemens.ajax.countryregion.ListRegionsAjax.js
*   
*   and
*   
*   http://localhost:8080/Root/Shop/com.siemens.ajax.countryregion.ListRegionsAjax.js
*   
*   will both work and load the same resource.
*   
*   Reguest parameters may be passed to the filter.  If the request parameter matches a public String
*   field on the view class, the ViewFilter will set the public field to the request paramter value.
*   For example, if ListRegionsAjax.java has a field <code>public String country_id</code>, 
*   the request
*   
*   http://localhost:8080/Root/com.siemens.ajax.countryregion.ListRegionsAjax.js?country_id=country
*   
*   will set the <code>country_id</code> field to the String "country" before interpreting ListRegionsAjax.js.
*/
public class ViewFilter implements Filter
{
	protected List fieldConstructionStrategies;
	
	protected MimeTypeMap fieldMimeTypes;
	
	protected List getConstructionStrategies()
	{
		if ( fieldConstructionStrategies == null )
		{
			fieldConstructionStrategies = new ArrayList();
		}
		return fieldConstructionStrategies;
	}
	
	/**
	 * Override this method in a subclass to customize ConstructionStrategies
	 * 
	 * The ViewFilter implementation adds a single strategy:
	 * 
	 * getConstructionStrategies().add( new PageContextConstructionStrategy() );
	 * 
	 */
	protected void addConstructionStrategies()
	{
		getConstructionStrategies().add( new PageContextConstructionStrategy() );
	}
	
	public void destroy()
	{
	}

	public void init( FilterConfig filterconfig ) throws ServletException
	{
		addConstructionStrategies();
	}

	public void doFilter( ServletRequest servletrequest, ServletResponse servletresponse,
			FilterChain filterchain ) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)servletrequest;
		HttpServletResponse resp = (HttpServletResponse) servletresponse;
		req.setCharacterEncoding( "UTF-8" );
		resp.setCharacterEncoding( "UTF-8" );
		
		String uri = req.getRequestURI();

		String base = uri.substring( uri.lastIndexOf( '/' ) + 1 );
		if ( base.length() == 0 || base.lastIndexOf( '.' ) < 0 )
		{
			filterchain.doFilter( servletrequest, servletresponse );
			return;
		}
		String extension = base.substring( base.lastIndexOf( '.' ) );
		String type = base.substring( 0, base.lastIndexOf( '.' ) );

		resp.addHeader( "Pragma", "no-cache" );
		resp.addHeader( "Cache-Control", "no-store, no-cache, must-revalidate, max-age=0" );
		resp.setContentType( contentType( extension ) );
		resp.setCharacterEncoding( "UTF-8" );

		Class typeClass;
		try
		{
			typeClass = resolveClass( type, extension );
		}
		catch ( ClassNotFoundException e )
		{
//			printer.println( "Type " + type + " not found" );
			filterchain.doFilter( servletrequest, servletresponse );
			return;
		}

		ResourceViewRenderer renderer = new ResourceViewRenderer();
		ResponseStream printer = new ResponseStream( resp );
		renderer.setPrintStream( printer );
		renderer.setExtension( extension );
		try
		{
			Object view = createView( typeClass, req, resp );
			populateSessionObjects( req.getSession(), view );
			scrape( req.getParameterMap(), view );
			renderer.setView( view );
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}

		renderer.render();
	}

	protected Class resolveClass( String type, String extension ) throws ClassNotFoundException
	{
		try
		{
			return Class.forName( type );
		}
		catch ( ClassNotFoundException e )
		{
			throw e;
		}
	}
	
	protected String contentType( String extension )
	{
		return getMimeTypes().type( extension );
	}

	protected void populateSessionObjects( HttpSession session, Object view )
	{
		SessionObjectManager objectManager = SessionObjectManager.sessionInstance( session );
		objectManager.populate( session, view );
	}

	protected void scrape( Map parameters, Object view )
	{
		Scraper scraper = new Scraper();
		scraper.setObject( view );
		scraper.scrape( parameters );
	}

	protected Object createView( Class inClass, HttpServletRequest req, HttpServletResponse resp )
	{
		Object sessionInstance = req.getSession().getAttribute( inClass.getName() );
		if ( sessionInstance != null )
		{
			return sessionInstance;
		}
		Constructor[] constructors = inClass.getConstructors();
		for ( int i = 0; i < constructors.length; i++ )
		{
			for ( Iterator iterator = getConstructionStrategies().iterator(); iterator.hasNext(); )
			{
				ConstructionStrategy strategy = (ConstructionStrategy) iterator.next();
				if ( strategy.matches( constructors[i] ) )
				{
					return strategy.createInstance( constructors[i], req, resp );
				}
			}
		}
		try
		{
			return inClass.newInstance();
		}
		catch ( Exception e )
		{
			throw new ObjectConstructionException(e);
		}
	}

	public MimeTypeMap getMimeTypes()
	{
		if ( fieldMimeTypes == null )
		{
			fieldMimeTypes = new MimeTypeMap();
		}

		return fieldMimeTypes;
	}
}
