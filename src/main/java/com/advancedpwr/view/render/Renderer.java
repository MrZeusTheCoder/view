package com.advancedpwr.view.render;

import java.io.PrintStream;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 4, 2011
*/
public interface Renderer
{
	String getResourceFileName();

	void render( Object result );

	Object getView();
	
	PrintStream getPrintStream();
	
	void setIndent( String inWhitespace );
	
	String getIndent();
}