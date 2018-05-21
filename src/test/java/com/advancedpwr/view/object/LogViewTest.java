/**
 * 
 */
package com.advancedpwr.view.object;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Aug 17, 2012
 *
 */
public class LogViewTest extends TestCase
{
	public void testLogView()
	{
		LogView logView = new LogView( new ObjectViewTest().createShip() );
		assertEquals( "Crewman:\n" + 
				"  Name = James T. Kirk\n" + 
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
