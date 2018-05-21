/**
 * 
 */
package com.advancedpwr.view.object;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 31, 2013
 *
 */
public class CondensedLogView extends LogView
{

	/**
	 * @param inSource
	 */
	public CondensedLogView( Object inSource )
	{
		super( inSource );
	}

	protected boolean isBlank( Object value )
	{
		if ( value instanceof String )
		{
			return value == null || ((String)value).trim().length() == 0;
		}
		return value == null;
	}

	
}
