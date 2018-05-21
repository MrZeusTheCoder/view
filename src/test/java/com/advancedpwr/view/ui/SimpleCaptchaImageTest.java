package com.advancedpwr.view.ui;

import java.awt.image.BufferedImage;

import junit.framework.TestCase;

public class SimpleCaptchaImageTest extends TestCase
{

	public void testSkewImage()
	{
		SimpleCaptchaImage image = new SimpleCaptchaImage();
		BufferedImage bi = image.skewImage( "5+3=" );
		assertEquals( SimpleCaptchaImage.IMAGE_HEIGHT, bi.getHeight() );
	}

}
