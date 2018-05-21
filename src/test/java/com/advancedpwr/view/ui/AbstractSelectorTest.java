package com.advancedpwr.view.ui;

import java.util.List;

import junit.framework.TestCase;

public class AbstractSelectorTest extends TestCase
{
	public void testId()
	{
		AbstractSelector selector = new AbstractSelector()
		{
			
			protected List<Option> loadOptions()
			{
				return null;
			}
			
			protected String key()
			{
				return "test";
			}
			
			
		};
		
		assertEquals( "test", selector.id() );
		selector.setTitle( null );
		assertEquals( "", selector.title() );
	}
}
