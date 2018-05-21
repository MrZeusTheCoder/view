package com.advancedpwr.action.controller;

import java.io.Serializable;
import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.advancedpwr.view.render.RenderingListener;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 17, 2011
*/
public class ActionMap extends LinkedHashMap<String, LiveAction> implements RenderingListener, Serializable
{
	private static final long serialVersionUID = -5694034951725549846L;
	
	protected static Logger fieldLogger;

	protected Logger getLogger()
	{
		if ( fieldLogger == null )
		{
			fieldLogger = LoggerFactory.getLogger( getClass() );
		}
		return fieldLogger;
	}
	
	protected LiveAction fieldCurrentAction;
	
	protected LiveAction fieldNextAction;
	
	public void renderEvent( Object inResult )
	{
		if ( LiveAction.class.isAssignableFrom( inResult.getClass() ) )
		{
			LiveAction action = (LiveAction) inResult;
			addAction( action );
		}
	}

	public LiveAction addAction( LiveAction action )
	{
		return put( action.key(), action );
	}

	
	public boolean hasLiveAction( Action inAction )
	{
		return containsKey( inAction.getActionMethod() );
	}
	
	public boolean executeLiveAction( Action inAction )
	{
		if ( hasLiveAction( inAction ) )
		{
			setCurrentAction( get( inAction.getActionMethod() ) );
			executeCurrentAction();
			return true;
		}
		else if ( getNextAction() != null )
		{
			setCurrentAction( getNextAction() );
			executeCurrentAction();
			setNextAction( null );
			return true;
		}
		return false;
	}
	
	protected void executeCurrentAction()
	{
		info(  "Executing action " + getCurrentAction().key() );
		getCurrentAction().execute();
	}

	protected void info( String message )
	{
		getLogger().info(  message );
	}
	public LiveAction getCurrentAction()
	{
		return fieldCurrentAction;
	}

	public void setCurrentAction( LiveAction currentAction )
	{
		fieldCurrentAction = currentAction;
		addAction( fieldCurrentAction );
	}

	public LiveAction getNextAction()
	{
		return fieldNextAction;
	}

	public void setNextAction( LiveAction defaultAction )
	{
		fieldNextAction = defaultAction;
	}

}
