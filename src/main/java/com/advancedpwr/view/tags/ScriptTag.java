package com.advancedpwr.view.tags;

import java.util.ArrayList;
import java.util.List;

import com.advancedpwr.action.controller.PathSupport;
import com.advancedpwr.view.render.ExtensionProvider;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jul 14, 2010
*/
public class ScriptTag implements PathSupport
{
	protected String fieldApplicationPath;
	
	protected List fieldElements;

	protected Object fieldSrc;

	public Object getSrc()
	{
		return fieldSrc;
	}

	public void setSrc( Object src )
	{
		fieldSrc = src;
	}
	
	
	protected String applicationPath()
	{
		if ( getApplicationPath().length() > 0 && !getApplicationPath().endsWith( "/" ))
		{
			return getApplicationPath() + "/";	
		}
		return getApplicationPath();
	}

	public List elements()
	{
		if ( fieldElements == null )
		{
			fieldElements = new ArrayList();
		}
		return fieldElements;
	}

	public void add( Object inElement )
	{
		if ( elements().size() == 0 )
		{
			elements().add( "" );
		}
		if ( inElement instanceof String
		  || isScript( inElement ) )
		{
			elements().add( inElement );
		}
		else
		{
			Script script = createScript();
			script.setObject( inElement );
			elements().add( script );
		}
	}

	protected boolean isScript( Object inElement )
	{
		if ( inElement == null )
		{
			return false;
		}
		return createScript().getClass().equals( inElement.getClass() );
	}

	protected Script createScript()
	{
		return new Script();
	}

	public Object src()
	{
		Attribute attribute = sourceAttribute();
		
		if ( getSrc() != null )
		{
			if ( getSrc() instanceof String )
			{
				attribute.setValue(  applicationPath() + getSrc().toString() );
			}
			else
			{
				attribute.setValue(  applicationPath() + getSrc().getClass().getName() + suffix() );
			}
		}
		return attribute;
	}

	protected String suffix()
	{
		if ( getSrc() instanceof ExtensionProvider )
		{
			return ((ExtensionProvider)getSrc()).extension();
		}
		return createScript().extension();
	}

	protected Attribute sourceAttribute()
	{
		return new SrcAttribute();
	}
	
	public String getApplicationPath()
	{
		if ( fieldApplicationPath == null )
		{
			fieldApplicationPath = "";
		}
		return fieldApplicationPath;
	}

	public void setApplicationPath( String applicationPath )
	{
		fieldApplicationPath = applicationPath;
	}

}
