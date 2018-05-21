package com.advancedpwr.action.controller;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 29, 2011
*/
public class ActionMapTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testRenderEvent()
	{
		ActionMap map = new ActionMap();
		map.renderEvent( new Object() );
		assertEquals( 0, map.size() );
		LiveAction action = createAction();
		map.renderEvent( action );
		assertEquals( 1, map.size() );
	}

	protected LiveAction createAction()
	{
		LiveAction action = new LiveAction()
		{
			@Override
			public void execute()
			{
			}
			
		};
		return action;
	}

	public void testExecuteLiveAction()
	{

		ActionMap map = new ActionMap();
		Action action = new Action();
		action.ACTION = "start";
		map.setCurrentAction( createAction() );
		assertFalse( map.executeLiveAction( action ) );
		
		final List actionExecutes = new ArrayList();
		LiveAction startAction = new LiveAction()
		{
			
			@Override
			public void execute()
			{
				actionExecutes.add( this );
			}
			
			@Override
			public String key()
			{
				return "start";
			}
			
		};
		
		map.addAction( startAction );
		assertEquals( 0, actionExecutes.size() );
		map.executeLiveAction( action );
		assertEquals( 1, actionExecutes.size() );
	}

}
