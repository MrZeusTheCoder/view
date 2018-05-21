package com.advancedpwr.action.controller;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 29, 2011
*/
public class NoOpTransformer implements StringTransformer
{

	public String transform( String inSource )
	{
		return inSource;
	}

}
