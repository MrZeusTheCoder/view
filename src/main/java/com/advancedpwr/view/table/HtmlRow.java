/**
 * 
 */
package com.advancedpwr.view.table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Sep 26, 2013
 *
 */
public class HtmlRow extends StyledObject
{
	protected List fieldCells;
	
	
	public List getCells()
	{
		if ( fieldCells == null )
		{
			fieldCells = new ArrayList();
			
		}

		return fieldCells;
	}
	

	public Object cells()
	{
		return getCells();
	}

	public void add( Object cell )
	{
		getCells().add( cell );
	}


}
