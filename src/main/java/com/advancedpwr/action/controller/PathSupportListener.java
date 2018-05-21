/**
 * 
 */
package com.advancedpwr.action.controller;

import com.advancedpwr.view.render.RenderingListener;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 24, 2013
 *
 */
public class PathSupportListener implements RenderingListener
{
	protected String fieldApplicationPath;
	/* (non-Javadoc)
	 * @see com.advancedpwr.view.render.RenderingListener#renderEvent(java.lang.Object)
	 */
	public void renderEvent( Object inResult )
	{
		if ( needsApplicationPath( inResult ) )
		{
			((PathSupport)inResult).setApplicationPath( getApplicationPath() );
		}
	}
	
	protected boolean needsApplicationPath( Object inResult )
	{
		return inResult != null && PathSupport.class.isAssignableFrom( inResult.getClass() );
	}
	
	public String getApplicationPath()
	{
		return fieldApplicationPath;
	}
	public void setApplicationPath( String applicationPath )
	{
		fieldApplicationPath = applicationPath;
	}

}
