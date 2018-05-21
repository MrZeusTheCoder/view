package com.advancedpwr.action.config;

import java.io.File;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Nov 19, 2010
*  
*  Although this class is for testing, it is generally useful in development and could be
*  used for launching an appserver in the local environment.
*/
public class SystemConnection
{
	public static final String SERVER_NAME = "server.name";

	public static void setTest()
	{
		System.setProperty( SERVER_NAME, "test" );
	}
	
	public static boolean isTest()
	{
		return "test".equals( System.getProperty( SERVER_NAME ) );
	}
	
	public static void setDev()
	{
		System.setProperty( SERVER_NAME, "dev" );
	}
	
	public static void setProd()
	{
		System.setProperty( SERVER_NAME, "prod" );
	}
	
	public static String configDirectory()
	{
		String fileName = "properties" + File.separator + serverName();
		File file = new File( fileName );
		return file.getAbsolutePath();
	}

	public static String serverName()
	{
		String serverName = System.getProperty( SERVER_NAME );
		if( serverName == null )
		{
			serverName = "local";
		}
		return serverName;
	}
	
	public static void clear()
	{
		System.clearProperty( SERVER_NAME );
	}
}
