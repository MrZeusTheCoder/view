/**
 * 
 */
package com.advancedpwr.view.controls;

import com.advancedpwr.view.render.RendererUtility;
import com.advancedpwr.view.ui.date.DateInput;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Feb 19, 2013
 *
 */
public class DateInputTest extends TestCase
{
	public void testImageTag()
	{
		RendererUtility util = new RendererUtility();
		assertEquals( "<img src=\"com.advancedpwr.view.ui.date.CalendarIcon.png\"/>", util.renderHtml( new DateInput().imageTag() ) ); 
	}
}
