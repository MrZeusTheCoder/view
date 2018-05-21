/**
 * 
 */
package com.advancedpwr.view.filter;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Dec 9, 2011
 *
 */
public class SessionObjectManager extends ArrayList<ViewPopulator>
{
	private static final long serialVersionUID = -7346945069687886463L;

	public static SessionObjectManager sessionInstance( HttpSession session )
	{
		SessionObjectManager manager = (SessionObjectManager)session.getAttribute( key() );
		if ( manager == null )
		{
			manager = new SessionObjectManager();
			session.setAttribute( key(), manager );
		}
		return manager;
	}

	protected static String key()
	{
		return SessionObjectManager.class.getName();
	}

	public void populate( HttpSession session, Object view )
	{
		for ( ViewPopulator populator : this )
		{
			if ( populator.matches( view ) )
			{
				populator.populate( session, view );
			}
		}
	}
	
}
