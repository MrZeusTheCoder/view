package com.advancedpwr.view.render;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 27, 2011
*/
public class RegexInterpreterTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testExecuteCall_IllegalAccessException() throws Exception
	{
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setView( this );
		RegexInterpreter interpreter = new RegexInterpreter( renderer );
		
		
		
		ViewCall call = new ViewCall()
		{

			public Object execute( Object inTarget ) throws NoSuchMethodException,
					IllegalAccessException, InvocationTargetException
			{
				throw new IllegalAccessException( "Bad stuff!" );
			}
			
		};
		call.setSource( "$view.badstuff()" );
		try
		{
			interpreter.executeCall( call );
			fail( "Should have thrown an Exception" );
		}
		catch( InterpreterException e )
		{
			assertTrue( e.getCause() instanceof IllegalAccessException );
			assertTrue( e.getCause().getMessage().contains( "Bad stuff!" ) );
		}
		
	}
	
	public void testCharsets()
	{
		System.out.println( Charset.defaultCharset() );
		
		SortedMap map = Charset.availableCharsets();
		for ( Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); )
		{
			Map.Entry type = (Map.Entry) iterator.next();
			System.out.println( type.getKey() );
		}
	}

}
