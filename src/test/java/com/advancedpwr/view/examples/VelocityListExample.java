package com.advancedpwr.view.examples;

import java.util.ArrayList;
import java.util.List;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Feb 7, 2011
*/
public class VelocityListExample
{
	public List listCars()
	{
		List list = new ArrayList();
		list.add(  createCarTableRow( "Cavalier", "$10,000.00" ) );
		list.add(  createCarTableRow( "Jeep", "$15,000.00" ) );
		list.add(  createCarTableRow( "F-150", "$20,000.00" ) );
		return list;
	}

	protected CarTableRow createCarTableRow( String inName, String inPrice )
	{
		CarTableRow row = new CarTableRow();
		row.setName( inName );
		row.setPrice( inPrice );
		return row;
	}
}
