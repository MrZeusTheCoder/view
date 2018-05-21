package com.advancedpwr.view.render;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class SourceLoader
{
	private static final String HTML = "html";
	protected Class fieldSourceClass;
	protected String fieldExtension;
	protected String fieldInputName;
	
	public Class getSourceClass()
	{
		return fieldSourceClass;
	}
	public void setSourceClass( Class sourceClass )
	{
		fieldSourceClass = sourceClass;
	}
	public String getExtension()
	{
		if ( fieldExtension == null )
		{
			fieldExtension = HTML;
		}
		return fieldExtension;
	}
	public void setExtension( String extension )
	{
		fieldExtension = extension;
	}
	
	public InputStream inputStream()
	{
		URL url = getClass().getResource( getInputName() );
		if ( url == null )
		{
			return null;
		}
		if ( "file".equals( url.getProtocol() ) )
		{
			//Development - load the resource every time
			//Will this be cached by the ClassLoader?
			try
			{
				return url.openStream();
			}
			catch ( IOException e )
			{
				return null;
			}
		}
		//Production - take advantage of ClassLoader caching
		return getClass().getResourceAsStream( getInputName() );
	}
	
	public String inputName()
	{
		String root = getSourceClass().getName().replace( '.', '/' );
		return "/" + root + getExtension();
	}
	public String getInputName()
	{
		if ( fieldInputName == null )
		{
			fieldInputName = inputName();
		}
		return fieldInputName;
	}
	public void setInputName( String inputName )
	{
		fieldInputName = inputName;
	}
}
