package com.advancedpwr.view.render;

import com.advancedpwr.view.examples.PriceTable;

import junit.framework.TestCase;

public class RendererUtilityTest extends TestCase
{
	public void testRenderHtml()
	{
		RendererUtility util = new RendererUtility();
		String output = util.renderHtml( new PriceTable() );
		assertEquals( "<table>\n" + 
				"	<tr>\n" + 
				"	<td>Item</td>\n" + 
				"	<td>Price</td>\n" + 
				"	</tr>\n" + 
				"	<tr><td>Widget</td><td>$10.00</td></tr>\n" + 
				"</table>", output );
	}
	
	public void testRender()
	{
		RendererUtility util = new RendererUtility();
		String output = util.render( new PriceTable(), ".js" );
		assertEquals( "function sortTable()\n" + 
				"{\n" + 
				"	// do something to sort the table\n" + 
				"}", output );
	}
}
