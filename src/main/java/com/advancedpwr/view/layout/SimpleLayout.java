package com.advancedpwr.view.layout;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 2, 2011
*/
public class SimpleLayout implements Layout
{

	protected Object fieldView;
	
	protected String fieldContextPath;
	
	public void wrap( Object inView )
	{
		setView( inView );
	}

	public Object getView()
	{
		return fieldView;
	}

	public void setView( Object view )
	{
		fieldView = view;
	}

	public String getContextPath()
	{
		return fieldContextPath;
	}

	public void setContextPath( String contextPath )
	{
		fieldContextPath = contextPath;
	}

	protected String contextPath()
	{
		if ( getContextPath() == null )
		{
			return "";
		}
		return getContextPath() + "/";
	}
}
