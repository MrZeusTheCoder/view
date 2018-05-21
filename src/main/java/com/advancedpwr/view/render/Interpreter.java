package com.advancedpwr.view.render;

import java.io.IOException;
import java.io.InputStream;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 4, 2011
*/
public interface Interpreter
{
	public abstract void interpret( InputStream in ) throws IOException;

}