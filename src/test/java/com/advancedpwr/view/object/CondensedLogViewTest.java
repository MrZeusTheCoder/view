/**
 * 
 */
package com.advancedpwr.view.object;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Feb 1, 2013
 *
 */
public class CondensedLogViewTest extends TestCase
{
	public void testLogView()
	{
		Ship ship =  new ObjectViewTest().createShip();
		ship.getCaptain().setName( " " );
		CondensedLogView logView = new CondensedLogView( ship );
		//TODO: fix this.  The ship name looks like the name of Crewman[2]
		assertEquals( "Crewman:\n" + 
				"  isHuman = true\n" + 
				"  CommissionDate = 11/20/2286\n" + 
				"  ContractNumber = 1701\n" + 
				"Crewman[0]:\n" + 
				"  Name = Spock\n" + 
				"  isHuman = false\n" + 
				"Crewman[1]:\n" + 
				"  Name = Dr. Leonard McCoy\n" + 
				"  isHuman = true\n" + 
				"Crewman[2]:\n" + 
				"  Name = Enterprise", logView.toString() );
	}
}
