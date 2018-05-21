package com.advancedpwr.view.tags;

import com.advancedpwr.view.render.RendererUtility;

import junit.framework.TestCase;

public class ImageTagTest extends TestCase
{
	public void testImageTag()
	{
		ImageTag tag = new ImageTag();
		tag.setSrc( "image.jpg" );
		RendererUtility util = new RendererUtility();
		String result = util.renderHtml( tag );
		assertEquals( "<img src=\"image.jpg\"/>", result);
	}
}
