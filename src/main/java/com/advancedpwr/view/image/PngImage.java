package com.advancedpwr.view.image;

import com.advancedpwr.view.render.ExtensionProvider;
import com.advancedpwr.view.render.Streamed;

public class PngImage implements ExtensionProvider, Streamed
{

	public String extension()
	{
		return ".png";
	}
	
}
