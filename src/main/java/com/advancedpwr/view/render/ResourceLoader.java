package com.advancedpwr.view.render;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ResourceLoader
{
	protected String fieldInputName;
	protected String fieldPath;
	
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
		return getPath();
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

	public String getPath()
	{
		return fieldPath;
	}

	public void setPath( String path )
	{
		fieldPath = path;
	}
}
