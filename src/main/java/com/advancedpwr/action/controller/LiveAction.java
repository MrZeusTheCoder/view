package com.advancedpwr.action.controller;

import java.util.zip.Adler32;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 13, 2011
*/
public abstract class LiveAction
{
	public abstract void execute();
	
	public static boolean DEV = "file".equals( LiveAction.class.getClassLoader().getResource( LiveAction.class.getName().replace( ".", "/" ) + ".class" ).getProtocol() );
	
	
	/** The toString() method has been overridden to output a only the class name,
	 *  not the hashCode, so that the action value does not change on each page view.
	 *  This has nothing to do with functionality.  This is implemented so that test
	 *  scripts will not break due to values changing within the HTML.
	 */
	public String toString()
	{
		if ( isDev() )
		{
			return getClass().getName();
		}
		// could use a checksum here instead
		Adler32 checksum = new Adler32();
		checksum.update( getClass().getName().getBytes() );
		return checksum.getValue() + "";
	}
	
	protected boolean isDev()
	{
		return DEV;
	}
	
	public String key()
	{
		return toString();
	}
}
