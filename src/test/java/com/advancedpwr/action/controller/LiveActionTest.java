/**
 * 
 */
package com.advancedpwr.action.controller;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Oct 2, 2012
 *
 */
public class LiveActionTest extends TestCase
{
	public void testKey()
	{
		LiveAction action = new LiveAction()
		{
			
			public void execute()
			{
			}
			
		};
		
		assertEquals( "com.advancedpwr.action.controller.LiveActionTest$1", action.key() );
		LiveAction.DEV = false;
		assertEquals( "4039185196", action.key() );
	}
	
}
