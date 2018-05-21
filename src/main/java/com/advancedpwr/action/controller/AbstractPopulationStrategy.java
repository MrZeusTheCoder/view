package com.advancedpwr.action.controller;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*/
public abstract class AbstractPopulationStrategy implements PopulationStrategy
{

	protected StringTransformer fieldTransformer;

	protected String inputName( Input input )
	{
		return input.getKey();
	}

	protected Object transform( String inSource )
	{
		return getTransformer().transform( inSource );
	}

	public StringTransformer getTransformer()
	{
		if ( fieldTransformer == null )
		{
			fieldTransformer = new NoOpTransformer();
		}
		return fieldTransformer;
	}

	public void setTransformer( StringTransformer transformer )
	{
		fieldTransformer = transformer;
	}

}
