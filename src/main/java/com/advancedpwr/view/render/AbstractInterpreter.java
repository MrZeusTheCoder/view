package com.advancedpwr.view.render;

import java.io.PrintStream;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 4, 2011
*/
public abstract class AbstractInterpreter implements Interpreter
{

	public AbstractInterpreter( Renderer inRenderer )
	{
		setRenderer( inRenderer );
	}
	
	protected Renderer fieldRenderer;

	public Object getView()
	{
		return getRenderer().getView();
	}

	public String getResourceFileName()
	{
		return getRenderer().getResourceFileName();
	}

	public Renderer getRenderer()
	{
		return fieldRenderer;
	}

	public void setRenderer( Renderer renderer )
	{
		fieldRenderer = renderer;
	}

	protected PrintStream getPrintStream()
	{
		return getRenderer().getPrintStream();
	}

}
