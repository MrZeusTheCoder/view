package com.advancedpwr.action.controller;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on May 4, 2011
*  
*  Calls setXXX( String ) accessor methods on the target object.
*  
*  This class reproduces the classic SDRC reflection behavior found in ReflectionUtils
*  
*/
public class AccessorReflectionStrategy extends MethodReflectionStrategy
{

	protected String methodName( Input input )
	{
		return "set" + upCaseInitial( inputName( input ) );
	}
		
	protected String upCaseInitial(String value) 
	{
		char initial = value.charAt(0);
		initial = Character.toUpperCase(initial);
		return initial + value.substring(1);
	}
	

}
