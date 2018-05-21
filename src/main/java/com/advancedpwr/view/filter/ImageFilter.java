package com.advancedpwr.view.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancedpwr.view.render.SourceLoader;

public class ImageFilter extends ViewFilter
{
	public void doFilter( ServletRequest servletrequest, ServletResponse servletresponse,
			FilterChain filterchain ) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest)servletrequest;
		HttpServletResponse resp = (HttpServletResponse) servletresponse;
		
		String uri = req.getRequestURI();

		String base = uri.substring( uri.lastIndexOf( '/' ) + 1 );
		if ( base.length() == 0 || base.lastIndexOf( '.' ) < 0 )
		{
			filterchain.doFilter( servletrequest, servletresponse );
			return;
		}
		String extension = base.substring( base.lastIndexOf( '.' ) );
		String type = base.substring( 0, base.lastIndexOf( '.' ) );

		resp.setContentType( contentType( extension ) );


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
		
		
		SourceLoader loader = new SourceLoader();
		loader.setSourceClass( typeClass );
		loader.setExtension( extension );
		InputStream in = loader.inputStream();
		
		OutputStream out = resp.getOutputStream();
		
		byte[] buffer = new byte[4096];
		int length = in.read(buffer);
		while ( length > 0)
		{
			out.write( buffer, 0, length );
			length = in.read( buffer );
		}
		out.flush();
		out.close();
	}
}
