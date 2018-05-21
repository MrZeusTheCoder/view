package com.advancedpwr.view.tags;

import com.advancedpwr.view.render.RendererUtility;

import junit.framework.TestCase;

public class JavascriptTagTest extends TestCase
{
	public void testSrc()
	{
		JavascriptTag tag = new JavascriptTag();
		assertEquals( tag, tag.getSrc() );
	}
	
	public void testTag()
	{
		RendererUtility util = new RendererUtility();
		assertEquals(  "<script type=\"text/javascript\" src=\"com.advancedpwr.view.tags.JavascriptTag.js\"></script>", util.renderHtml( new JavascriptTag() ) );
	}
}
