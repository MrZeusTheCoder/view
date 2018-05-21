package com.advancedpwr.action.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 15, 2011
*/
public class ServletResponseStub implements ServletResponse
{

	public void flushBuffer() throws IOException
	{
	}

	public int getBufferSize()
	{
		return 0;
	}

	public String getCharacterEncoding()
	{
		return "UTF-8";
	}

	public String getContentType()
	{
		return null;
	}

	public Locale getLocale()
	{
		return null;
	}

	public ServletOutputStream getOutputStream() throws IOException
	{
		return new ServletOutputStream()
		{
			
			@Override
			public void write( int b ) throws IOException
			{
			}

			public boolean isReady()
			{
				return false;
			}

			public void setWriteListener( WriteListener arg0 )
			{
			}
		};
	}

	public PrintWriter getWriter() throws IOException
	{
		return null;
	}

	public boolean isCommitted()
	{
		return false;
	}

	public void reset()
	{
	}

	public void resetBuffer()
	{
	}

	public void setBufferSize( int arg0 )
	{
	}

	public void setCharacterEncoding( String arg0 )
	{
	}

	public void setContentLength( int arg0 )
	{
	}

	public void setContentType( String arg0 )
	{
	}

	public void setLocale( Locale arg0 )
	{
	}

	public void setContentLengthLong( long arg0 )
	{
	}

}
