/**
 * 
 */
package com.advancedpwr.view.object;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Apr 2, 2012
 *
 */
public class Crewman
{
	protected boolean fieldHuman;
	protected String fieldName;
	public boolean isHuman()
	{
		return fieldHuman;
	}
	public void setHuman( boolean human )
	{
		fieldHuman = human;
	}
	public String getName()
	{
		return fieldName;
	}
	public void setName( String name )
	{
		fieldName = name;
	}
	
}
