/**
 * 
 */
package com.advancedpwr.view.tags;

import com.advancedpwr.view.render.RendererUtility;

import junit.framework.TestCase;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * CreatedStamped: Jan 25, 2013
 *
 */
public class StyleTagTest extends TestCase
{
	public void testStyleTag()
	{
		
		StyleTag tag = new StyleTag();
		tag.add( new MyLook() );
		RendererUtility util = new RendererUtility();
		assertEquals( "<style>\n" + 
				"div\n" + 
				"{\n" + 
				"	display: block;\n" + 
				"}\n" + 
				"</style>", util.renderHtml( tag ) );
	}
}
