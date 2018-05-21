/**
 * 
 */
package com.advancedpwr.action.controller;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Feb 18, 2013
 *
 */
public class DynamicInputPopulationStrategy extends AbstractPopulationStrategy
{

	/* (non-Javadoc)
	 * @see com.advancedpwr.action.controller.PopulationStrategy#setValue(java.lang.Object, com.advancedpwr.action.controller.Input)
	 */
	public void setValue( Object inTarget, Input input )
	{
		if ( DynamicInput.class.isAssignableFrom( inTarget.getClass() ) )
		{
			DynamicInput target = (DynamicInput) inTarget;
			try
			{
				target.setValue( input );
			}
			catch ( Exception e )
			{
				throw new RuntimeException(e);
			}
		}
	}

}
