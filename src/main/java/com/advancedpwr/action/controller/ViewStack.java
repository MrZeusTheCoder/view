/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.Stack;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 15, 2012
 *
 */
public class ViewStack extends Stack
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6494413738439437002L;

	public Object previous()
	{
		if ( isEmpty() )
		{
			return null;
		}
		if ( size() > 1 )
		{
			pop();
		}
		return peek();
	}
	
	protected void addView( Object inView )
	{
		if ( isEmpty() )
		{
			push( inView );
			return;
		}
		Object lastView = peek();
		if ( lastView != null && !inView.getClass().equals( lastView.getClass() ) )
		{
			push( inView );
		}
	}
}
