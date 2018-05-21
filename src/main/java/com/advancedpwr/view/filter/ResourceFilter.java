package com.advancedpwr.view.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
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
import com.advancedpwr.view.render.ResourceLoader;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 28, 2017
*  
*  Add the following xml to web.xml to enable the ResourceFilter:
*  
  	<filter>
		<filter-name>ViewFilter</filter-name>
		<filter-class>com.advancedpwr.view.filter.ResourceFilter</filter-class>
	</filter>

	<filter-mapping>
		<filter-name>ViewFilter</filter-name>
		<url-pattern>*</url-pattern>
	</filter-mapping>

*
*	The ResourceFilter will load a static resource file from the classpath 
*
*   http://localhost:8080/Root/bootstrap/css/bootstrap.css
*   
*   and
*   
*   http://localhost:8080/Root/Shop/bootstrap/css/bootstrap.css
*   
*   will both work and load the same resource.
*   
*   
*/
public class ResourceFilter implements Filter
{

	protected MimeTypeMap fieldMimeTypes;
	
	public void destroy()
	{
	}

	public void init( FilterConfig filterconfig ) throws ServletException
	{

	}

	public void doFilter( ServletRequest servletrequest, ServletResponse servletresponse,
			FilterChain filterchain ) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)servletrequest;
		HttpServletResponse resp = (HttpServletResponse) servletresponse;
		req.setCharacterEncoding( "UTF-8" );
		resp.setCharacterEncoding( "UTF-8" );
		
		String uri = req.getRequestURI();
		String path = req.getContextPath();

		String base = uri.substring( uri.lastIndexOf( '/' ) + 1 );
		if ( base.length() == 0 || base.lastIndexOf( '.' ) < 0 )
		{
			filterchain.doFilter( servletrequest, servletresponse );
			return;
		}
		String extension = base.substring( base.lastIndexOf( '.' ) );

		resp.setContentType( contentType( extension ) );
		resp.setCharacterEncoding( "UTF-8" );


		ResponseStream printer = new ResponseStream( resp );
		
		InputStream in = inputStream( uri.replaceFirst( path, "" ) );
		if ( in != null )
		{
			stream( in, printer );
		}
		else
		{
			filterchain.doFilter( servletrequest, servletresponse );
		}
		
	}

	protected InputStream inputStream( String base )
	{
		
		ResourceLoader loader = new ResourceLoader();
		loader.setPath( base );
		InputStream in = loader.inputStream();
		if ( in == null )
		{
			int index = base.substring( 1 ).indexOf( "/" );
			if ( index > 0)
			{
				return inputStream( base.substring( index + 1 ) );
			}
			return null;
		}
		return in;
	}

	protected void stream( InputStream in, PrintStream out ) throws IOException
	{

			byte[] buffer = new byte[2048];
			int read = 0;
			while( (read = in.read( buffer )) > 0 )
			{
				out.write( buffer, 0, read );
			}
			out.flush();
			out.close();

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


	public MimeTypeMap getMimeTypes()
	{
		if ( fieldMimeTypes == null )
		{
			fieldMimeTypes = new MimeTypeMap();
		}

		return fieldMimeTypes;
	}
}
