/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.ArrayList;

import com.advancedpwr.view.render.RenderingListener;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jun 26, 2012
 *
 */
public class CompositeListener extends ArrayList<RenderingListener> implements RenderingListener
{

	/* (non-Javadoc)
	 * @see com.advancedpwr.view.render.RenderingListener#renderEvent(java.lang.Object)
	 */
	public void renderEvent( Object inResult )
	{
		for ( RenderingListener listener : this )
		{
			listener.renderEvent( inResult );
		}
	}

}
