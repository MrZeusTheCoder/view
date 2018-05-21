package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 15, 2011
*/
public class NamespaceFieldStrategyTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testStrategy()
	{
		NamespaceFieldStrategy strategy = new NamespaceFieldStrategy();
		strategy.setNameSpace( "native" );
		
		NativeInputForm nr = new NativeInputForm();
		Map map = new HashMap();
		map.put( "lastName", "Fred" );
		map.put( "native.lastName", "Joe" );
		
		Input lastName = new Input( map, "native.lastName" );
		
		strategy.setValue( nr, lastName );
		
		assertEquals( "Joe", nr.getLastName() );
		
	}
	
	public void testStrategy_miss()
	{
		NamespaceFieldStrategy strategy = new NamespaceFieldStrategy();
		strategy.setNameSpace( "native" );
		
		NativeInputForm nr = new NativeInputForm();
		Map map = new HashMap();
		map.put( "lastName", "Fred" );
		Input lastName = new Input( map, "lastName" );
		
		strategy.setValue( nr, lastName );
		
		assertEquals( null, nr.getLastName() );
		
	}
}
