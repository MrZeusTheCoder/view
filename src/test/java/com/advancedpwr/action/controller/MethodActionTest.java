package com.advancedpwr.action.controller;

import java.lang.reflect.InvocationTargetException;

import junit.framework.TestCase;

public class MethodActionTest extends TestCase
{
	class StringHolder
	{
		public String s;
	}
	
	protected void setUp()
	{
		
	}
	
	public void testHasAction()
	{
		Action action = new Action();
		action.ACTION = "getClass";
		
		MethodAction methodAction = new MethodAction( new Object(), action );
		assertTrue( methodAction.hasAction() );
		

	}
	
	public void testHasAction_false()
	{
		Action action = new Action();
		action.ACTION = "notFound";
		final StringHolder holder = new StringHolder();
		MethodAction methodAction = new MethodAction( new Object(), action )
		{

			@Override
			protected void warn( String message )
			{
				holder.s = message;
				super.warn( message );
			}
			
		};
		assertFalse( methodAction.hasAction() );
		
		assertNull( holder.s );
		// no bad consequences, just a log statement
		methodAction.execute();
		assertEquals( "Object.notFound() method not found! skipping action...", holder.s );
	}
	
	public void testExecute_ite()
	{
		final StringHolder holder = new StringHolder();
		
		Action a = new Action();
		a.ACTION = "getClass";
		MethodAction action = new MethodAction( new Object(), a )
		{

			@Override
			protected void invokeMethod() throws Exception
			{
				throw new InvocationTargetException( new NullPointerException() );
			}

			@Override
			protected void error( String message, Throwable t )
			{
				holder.s = message;
				super.error( message, t );
			}
			
			
			
		};
		
		try
		{
			action.execute();;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e instanceof NullPointerException );
		}
		assertEquals( "Error attempting to exectute MethodAction Object.getClass()", holder.s );
	}
	
	public void testExecute_exception()
	{
		final StringHolder holder = new StringHolder();
		
		Action a = new Action();
		a.ACTION = "getClass";
		MethodAction action = new MethodAction( new Object(), a )
		{

			@Override
			protected void invokeMethod() throws Exception
			{
				throw new Exception( "Bad stuff!" );
			}

			@Override
			protected void error( String message, Throwable t )
			{
				holder.s = message;
				super.error( message, t );
			}
			
			
			
		};
		
		try
		{
			action.execute();;
			fail( "Should have thrown an Exception" );
		}
		catch( Exception e )
		{
			assertTrue( e.getCause().getMessage().contains( "Bad stuff!" ) );
		}
		assertEquals( "Error attempting to exectute MethodAction Object.getClass()", holder.s );
	}

}
