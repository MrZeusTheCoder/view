package com.advancedpwr.action.controller;



/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 14, 2011
*/
public class NativeNamespaceScraper extends Scraper
{

	private static final String NATIVE = "native";
	
	public NativeNamespaceScraper()
	{
		setPopulationStrategy( createPopulationStrategy() );
	}

	protected NamespaceFieldStrategy createPopulationStrategy()
	{
		NamespaceFieldStrategy strategy = new NamespaceFieldStrategy();
		strategy.setNameSpace( NATIVE );
		return strategy;
	}
}
