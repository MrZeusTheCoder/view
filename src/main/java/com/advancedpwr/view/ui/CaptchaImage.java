package com.advancedpwr.view.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.jsp.PageContext;

import com.advancedpwr.action.controller.EncryptedRequestParameters;
import com.advancedpwr.action.controller.RequestParameters;
import com.advancedpwr.action.controller.Scraper;
import com.advancedpwr.view.AbstractView;
import com.advancedpwr.view.tags.ImageTag;

public class CaptchaImage extends AbstractView
{
	public String challenge;
	
	public CaptchaImage()
	{	
	}
	
	public CaptchaImage( PageContext inContext )
	{
		super( inContext );
		scrape();
	}
	
	protected void scrape()
	{
		Scraper scraper = new Scraper();
		scraper.setObject( this );
		scraper.scrape( getRequest().getParameterMap() );
	}

	public void streamImage() throws IOException
	{
		System.setProperty("java.awt.headless", "true");
		SimpleCaptchaImage skew = new SimpleCaptchaImage();
		BufferedImage image = skew.skewImage( getChallenge() );
		ImageIO.write( image, "jpeg", getOutputStream() );
		getOutputStream().flush();
		getOutputStream().close();
	}

	protected OutputStream getOutputStream() throws IOException
	{
		return getPageContext().getResponse().getOutputStream();
	}
	
	public ImageTag toImageTag()
	{
		ImageTag tag = new ImageTag();
		RequestParameters params = new EncryptedRequestParameters();
		params.addObject( this );
		tag.setSrc( getClass().getName() + ".jpg" + params.parameters()  );
		return tag;
	}

	public String getChallenge()
	{
		return challenge;
	}

	public void setChallenge( String inChallenge )
	{
		challenge = inChallenge;
	}
}