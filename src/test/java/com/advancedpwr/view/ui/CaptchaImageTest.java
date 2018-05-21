package com.advancedpwr.view.ui;

import com.advancedpwr.view.tags.ImageTag;

import junit.framework.TestCase;

public class CaptchaImageTest extends TestCase
{

	public void testToImageTag()
	{
		CaptchaImage captcha = new CaptchaImage();
		captcha.setChallenge( "test" );
		ImageTag tag = captcha.toImageTag();
		assertEquals( "com.advancedpwr.view.ui.CaptchaImage.jpg?c=lZyNIOqxYOEbfJ67nibrCQ%3D%3D", tag.getSrc().toString() );
	}



}
