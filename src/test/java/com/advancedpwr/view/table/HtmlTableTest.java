package com.advancedpwr.view.table;

import com.advancedpwr.view.render.RendererUtility;

import junit.framework.TestCase;

public class HtmlTableTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}
	
	public void testTable()
	{
		HtmlTable table = new HtmlTable();
		table.newRow();
		table.headerCell( "Row" );
		table.headerCell( "Name" );
		table.newRow();
		table.newCell( 1 );
		table.newCell( "Joe" );
		table.newRow();
		
		table.newCell( 2 );
		table.newCell( "Mary" );
		
		RendererUtility util = new RendererUtility();
		assertEquals( "<table class=\"table\">\n" + 
				"<tr class=\"rowOff\">\n" + 
				"  <th >Row</th>\n" + 
				"  <th >Name</th>\n" + 
				"</tr>\n" + 
				"<tr class=\"rowOn\">\n" + 
				"  <td >1</td>\n" + 
				"  <td >Joe</td>\n" + 
				"</tr>\n" + 
				"<tr class=\"rowOff\">\n" + 
				"  <td >2</td>\n" + 
				"  <td >Mary</td>\n" + 
				"</tr>\n" + 
				"</table>", util.renderHtml( table ) );
	}

}
