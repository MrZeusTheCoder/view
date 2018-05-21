/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Jan 24, 2012
 *
 */
public class NamespaceAccessorStrategyTest extends TestCase
{
	public void testScrape()
	{
		Scraper scraper = new Scraper();
		NamespaceAccessorStrategy strategy = new NamespaceAccessorStrategy();
		
		scraper.setPopulationStrategy( strategy );
	
		Map map = new HashMap();
		map.put( "string", "no namespace" );
		map.put( "namespace.string", "with namespace" );
		PrimitiveBean bean = new PrimitiveBean();
		
		scraper.setObject( bean );
		scraper.scrape( map );
		assertEquals( "no namespace", bean.getString() );
		
		strategy.setNamespace( "namespace" );
		scraper.scrape(map);
		assertEquals( "with namespace", bean.getString() );
		
		
		
	}
}
