/**
 * 
 */
package com.advancedpwr.action.controller;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 24, 2012
 *
 */
public class NamespaceAccessorStrategy extends AccessorReflectionStrategy
{
	protected String fieldNamespace;

	public String getNamespace()
	{
		return fieldNamespace;
	}

	public void setNamespace( String namespace )
	{
		fieldNamespace = namespace;
	}
	
	protected String inputName( Input input )
	{
		if ( getNamespace() == null )
		{
			super.inputName( input );
		}
		return input.getKey().replaceFirst( getNamespace() + ".", "" );
	}
}
