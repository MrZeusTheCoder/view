package com.advancedpwr.action.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jun 24, 2010
*  
*  This class takes an input parameter Map and maps the parameters onto the target
*  object using a {@link PopulationStrategy}.  The default {@link PopulationStrategy} is
*  {@link PublicFieldReflectionStrategy}.
*  
*  This class is also able to decode Base64 encoded input using the {@link Base64Parameters} class
*  or encrypted input parameters using the {@link EncryptedParameters} class.
*  
*  Base64 encoded request parameters are of the form "?k=<encoded parameters>" while encrypted
*  request parameters are of the form "?c=<encrypted parameters>".
*/
public class Scraper
{
	private static final Logger LOGGER = LoggerFactory.getLogger( Scraper.class );
	
	protected Object fieldObject;
	
	protected PopulationStrategy fieldPopulationStrategy;
	
	protected int fieldArrayIndex;

	public Object getObject()
	{
		return fieldObject;
	}

	public void setObject( Object object )
	{
		fieldObject = object;
	}

	public void scrape( Map inInputs )
	{
//		System.out.println( "Inputs size: " + inInputs.size() );
		Map allInputs = new HashMap();
		allInputs.putAll( inInputs );
		
		addBase64EncodedParameters( inInputs, allInputs );
		
		addEncryptedParameters( inInputs, allInputs );
		for ( Iterator iterator = allInputs.entrySet().iterator(); iterator.hasNext(); )
		{
			Map.Entry entry = (Map.Entry) iterator.next();
			Input input = createInput( allInputs, (String) entry.getKey() );
			LOGGER.debug( "key: " + input.getKey() + ", value: " + input.getValue() );
			if ( input.exists() )
			{
				setValue( input );
			}
		}
	}
	
	protected Input createInput( Map allInputs, String inKey )
	{
		if ( getArrayIndex() > 0 )
		{
			ArrayInput arrayInput = new ArrayInput( allInputs, inKey );
			arrayInput.setIndex( getArrayIndex() );
			return arrayInput;
		}
		return  new Input( allInputs, inKey );
	}

	protected void addEncryptedParameters( Map inInputs, Map allInputs )
	{
		EncryptedParameters encrypted = new EncryptedParameters( inInputs );
		if ( encrypted.exists() )
		{
			allInputs.putAll( encrypted.getParameters() );
		}
	}

	protected void addBase64EncodedParameters( Map inInputs, Map allInputs )
	{
		Base64Parameters encoded = new Base64Parameters( inInputs );
		if ( encoded.exists() )
		{
			allInputs.putAll( encoded.getParameters() );
		}
	}

	protected void setValue( Input input )
	{
		getPopulationStrategy().setValue( getObject(), input );
	}
	
	public PopulationStrategy getPopulationStrategy()
	{
		if ( fieldPopulationStrategy == null )
		{
			fieldPopulationStrategy = createPopulationStrategy();
		}
		return fieldPopulationStrategy;
	}

	protected PopulationStrategy createPopulationStrategy()
	{
		CompoundPopulationStrategy strategy = new CompoundPopulationStrategy();
		strategy.add( new PublicFieldReflectionStrategy() );
		strategy.add( new MethodReflectionStrategy() );
		strategy.add( new AccessorReflectionStrategy() );
		strategy.add( new DynamicInputPopulationStrategy() );
		return strategy;
	}

	public void setPopulationStrategy( PopulationStrategy populationStrategy )
	{
		fieldPopulationStrategy = populationStrategy;
	}

	public int getArrayIndex()
	{
		return fieldArrayIndex;
	}

	public void setArrayIndex( int arrayIndex )
	{
		fieldArrayIndex = arrayIndex;
	}
}
