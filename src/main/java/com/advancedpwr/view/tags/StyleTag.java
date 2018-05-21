/**
 * 
 */
package com.advancedpwr.view.tags;


/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 16, 2012
 *
 */
public class StyleTag extends ScriptTag
{

	protected Script createScript()
	{
		return new Style();
	}
}
