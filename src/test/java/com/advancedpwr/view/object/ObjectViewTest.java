package com.advancedpwr.view.object;

import java.util.Date;

import com.advancedpwr.view.render.RendererUtility;

import junit.framework.TestCase;

public class ObjectViewTest extends TestCase
{
	public void testRender()
	{
		Ship ship = createShip();
		
		ObjectView view = new ObjectView( ship );
		
		RendererUtility util = new RendererUtility();
		String out = util.renderHtml( view );
		
		assertEquals( "<div class=\"object\">Crewman\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"Name\">Name</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"Name\" type=\"text\" size=\"50\"  value=\"James T. Kirk\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"isHuman\">isHuman</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"isHuman\" type=\"text\" size=\"50\"  value=\"true\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"CommissionDate\">CommissionDate</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"CommissionDate\" type=\"text\" size=\"50\"  value=\"11/20/2286\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"ContractNumber\">ContractNumber</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"ContractNumber\" type=\"text\" size=\"50\"  value=\"1701\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"<div class=\"object\">Crewman\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"Name\">Name</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"Name\" type=\"text\" size=\"50\"  value=\"Spock\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"isHuman\">isHuman</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"isHuman\" type=\"text\" size=\"50\"  value=\"false\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"</div>\n" + 
				"<div class=\"object\">Crewman\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"Name\">Name</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"Name\" type=\"text\" size=\"50\"  value=\"Dr. Leonard McCoy\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"isHuman\">isHuman</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"isHuman\" type=\"text\" size=\"50\"  value=\"true\"/>\n" + 
				"  	</div>\n" + 
				"</div>\n" + 
				"</div>\n" + 
				"<div class=\"object\">Crewman\n" + 
				"</div>\n" + 
				"<div class=\"input\">\n" + 
				"	<div class=\"label\" for=\"Name\">Name</div>\n" + 
				"  	<div class=\"in\">\n" + 
				"  		<input name=\"Name\" type=\"text\" size=\"50\"  value=\"Enterprise\"/>\n" + 
				"  	</div>\n" + 
				"</div>", out);
		
	}

	public Ship createShip()
	{
		Ship ship = new Ship();
		ship.setName( "Enterprise" );
		ship.setCommissionDate( new Date( 9999999999999l ) );
		ship.setContractNumber( 1701 );
		Crewman captain = new Crewman();
		captain.setName( "James T. Kirk" );
		captain.setHuman( true );
		ship.setCaptain( captain );
		
		Crewman[] crew = new Crewman[3];
		Crewman spock = new Crewman();
		spock.setName( "Spock" );
		spock.setHuman( false );
		
		Crewman bones = new Crewman();
		bones.setName( "Dr. Leonard McCoy" );
		bones.setHuman( true );
		crew[0] = spock;
		crew[1] = bones;
		
		ship.setCrew( crew );
		return ship;
	}
	
	public void testRender_txt()
	{
		Ship ship = createShip();
		LogView view = new LogView( ship );
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
				"  Name = Enterprise", view.toString() );
		ship.setCaptain( null );
		
		assertEquals( "  CommissionDate = 11/20/2286\n" + 
				"  ContractNumber = 1701\n" + 
				"Crewman[0]:\n" + 
				"  Name = Spock\n" + 
				"  isHuman = false\n" + 
				"Crewman[1]:\n" + 
				"  Name = Dr. Leonard McCoy\n" + 
				"  isHuman = true\n" + 
				"Crewman[2]:\n" + 
				"  Name = Enterprise" , view.toString());
	}
}
