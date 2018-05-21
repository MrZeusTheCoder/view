/**
 * 
 */
package com.advancedpwr.action.controller;

import javax.servlet.http.HttpSession;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 24, 2013
 *
 */
public class SessionInstanceFactory
{
	public <T> T getSessionInstance( HttpSession session, Class<T> inClass )
	{
		T sessionInstance = (T) session.getAttribute( inClass.getName() );
		if ( sessionInstance == null )
		{
			sessionInstance = newInstance( inClass );
			session.setAttribute( inClass.getName(), sessionInstance );
		}
		return sessionInstance;
	}

	protected <T> T newInstance( Class<T> inClass )
	{
		try
		{
			return inClass.newInstance();
		}
		catch ( Exception e )
		{
			throw new RuntimeException( e );
		}
	}

}
