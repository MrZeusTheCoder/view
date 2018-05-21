/**
 * 
 */
package com.advancedpwr.action.controller;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Mar 15, 2012
 *
 */
public class ViewStackTest extends TestCase
{
	public void testAdd()
	{
		class View1{};
		class View2{};
		
		ViewStack stack = new ViewStack();
		stack.addView( new View1() );
		stack.addView( new View2() );
		assertEquals( 2, stack.size() );
		stack.addView( new View2() );
		assertEquals( 2, stack.size() );
		
		stack.addView( new View1() );
		assertEquals( 3, stack.size() );
		
		stack.addView( new View1() );
		assertEquals( 3, stack.size() );
		
	}
	
	public void testPrevious()
	{
		class View1{};
		class View2{};
		
		ViewStack stack = new ViewStack();
		assertNull( stack.previous() );
		View1 view1 = new View1();
		stack.addView( view1 );
		View2 view2 = new View2();
		stack.addView( view2 );
		assertEquals( 2, stack.size() );
		assertEquals( view1, stack.previous() );
		assertEquals( view1, stack.previous() );
		stack.addView( view1 );
		assertEquals( view1, stack.previous() );
		stack.addView( view2 );
		stack.addView( view1 );
		assertEquals( view2, stack.previous() );

		assertEquals( view1, stack.previous() );
		assertEquals( view1, stack.previous() );
	}
}
