/**
 * 
 */
package com.advancedpwr.view.tags;

import com.advancedpwr.view.render.ExtensionProvider;
import com.advancedpwr.view.render.RendererUtility;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Feb 1, 2012
 *
 */
public class Script implements ExtensionProvider
{
	private static final String JS = ".js";
	protected Object fieldObject;
	
	public String toString()
	{
		RendererUtility util = new RendererUtility();
		return util.render( getObject(), extension() );
	}

	public Object getObject()
	{
		return fieldObject;
	}

	public void setObject( Object object )
	{
		fieldObject = object;
	}

	public String extension()
	{
		return JS;
	}
}
