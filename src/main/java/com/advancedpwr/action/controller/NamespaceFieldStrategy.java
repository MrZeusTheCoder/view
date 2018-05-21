package com.advancedpwr.action.controller;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 14, 2011
*/
public class NamespaceFieldStrategy extends PublicFieldReflectionStrategy
{
	protected String fieldNameSpace;
	
	@Override
	protected String inputName( Input input )
	{
		return input.getKey().replaceFirst( getNameSpace() + ".", "" );
	}
	
	public String getNameSpace()
	{
		return fieldNameSpace;
	}
	
	public void setNameSpace( String nameSpace )
	{
		fieldNameSpace = nameSpace;
	}

	@Override
	public void setValue( Object inTarget, Input input )
	{
		if ( input.getKey().startsWith( getNameSpace() ) )
		{
			super.setValue( inTarget, input );
		}
	}
	
	

}
