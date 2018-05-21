/**
 * 
 */
package com.advancedpwr.view.table;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Sep 26, 2013
 *
 */
public class HtmlCell extends StyledObject
{
	protected Object fieldValue;
	
	public Object getValue()
	{
		return fieldValue;
	}

	public void setValue( Object value )
	{
		fieldValue = value;
	}
	
	public Object value()
	{
		if ( getValue() == null )
		{
			return "";
		}
		return getValue();
	}
	
}
