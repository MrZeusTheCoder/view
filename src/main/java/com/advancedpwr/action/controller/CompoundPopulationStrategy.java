/**
 * 
 */
package com.advancedpwr.action.controller;

import java.util.ArrayList;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Dec 7, 2011
 *
 */
public class CompoundPopulationStrategy extends ArrayList<PopulationStrategy> implements PopulationStrategy
{
	
	/* (non-Javadoc)
	 * @see com.advancedpwr.action.controller.PopulationStrategy#setValue(java.lang.Object, com.advancedpwr.action.controller.Input)
	 */
	public void setValue( Object inTarget, Input input )
	{
		for ( PopulationStrategy strategy : this )
		{
			strategy.setValue( inTarget, input );
		}
	}

}
