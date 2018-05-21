/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 15, 2012
 *
 */
public class CurrentPageActionTest extends TestCase
{
	class View1{};
	
	public void testCurrentPageAction()
	{
		
		
		final List views = new ArrayList();
		ViewManager viewManager = new ViewManager()
		{
			public void renderHtml( Object inView )
			{
				views.add(inView);
			}	
		};
		AbstractController controller = new AbstractController()
		{
		};
		
		controller.setViewManager( viewManager );
		CurrentPageAction action = new CurrentPageAction();
		action.setController( controller );
		assertEquals( 0, views.size() );
		action.execute();
		assertEquals( 0, views.size() );
		
		View1 view1 = new View1();
		viewManager.getViews().push( view1 );
		
		action.execute();
		
		assertEquals( view1, views.get(0) );
	}
}
