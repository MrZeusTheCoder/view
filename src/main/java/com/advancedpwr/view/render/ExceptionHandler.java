package com.advancedpwr.view.render;

import java.io.PrintStream;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Mar 22, 2011
*/
public interface ExceptionHandler
{

	void handleException( PrintStream stream, Exception e );

}
