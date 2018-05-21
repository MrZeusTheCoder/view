/**
 * 
 */
package com.advancedpwr.action.controller;

import javax.servlet.http.HttpSession;

import com.advancedpwr.view.render.RenderingListener;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jun 26, 2012
 *
 */
public class DynamicViewListener implements RenderingListener
{
	protected HttpSession fieldSession;
	
	
	/* (non-Javadoc)
	 * @see com.advancedpwr.view.render.RenderingListener#renderEvent(java.lang.Object)
	 */
	public void renderEvent( Object inResult )
	{
		if ( isDynamic( inResult ) )
		{
			getSession().setAttribute( inResult.getClass().getName(), inResult );
		}
	}
	protected boolean isDynamic( Object inResult )
	{
		return inResult != null && Dynamic.class.isAssignableFrom( inResult.getClass() );
	}
	public HttpSession getSession()
	{
		return fieldSession;
	}
	public void setSession( HttpSession session )
	{
		fieldSession = session;
	}
	
}
