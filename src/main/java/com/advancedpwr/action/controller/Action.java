package com.advancedpwr.action.controller;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jul 13, 2010
*/
public class Action
{
	public String ACTION;

	public boolean hasAction()
	{
		return ACTION != null;
	}

	public String getActionMethod()
	{
		if ( hasAction() )
		{
			return ACTION.trim();
		}
		return ACTION;
	}
	
	public void setACTION( String inAction )
	{
		ACTION = inAction;
	}
}
