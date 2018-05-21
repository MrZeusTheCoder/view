package com.advancedpwr.view.examples;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Jan 28, 2011
*/
public class SubClassExample1 extends PriceTable
{
	public String tableRows()
	{
		return "<tr>\n" +
				"	<td>Car</td>\n" +
				"	<td>$10,000.00</td>\n" +
				"</tr>";
	}
}
