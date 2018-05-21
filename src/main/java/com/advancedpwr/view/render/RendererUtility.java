package com.advancedpwr.view.render;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: May 17, 2011
 *
 * This class was created to facilitate testing.
 */
public class RendererUtility
{
	protected ByteArrayOutputStream out;
	
	public String renderHtml( Object inView )
	{
		HtmlViewRenderer renderer = new HtmlViewRenderer();
		renderer.setPrintStream( createPrintStream() );
		renderer.render( inView );
		return out.toString();
	}
	
	public String render( Object inView, String inExtension )
	{
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setPrintStream( createPrintStream() );
		renderer.setExtension( inExtension );
		renderer.render( inView );
		return out.toString();
	}
	
	protected PrintStream createPrintStream()
	{
		out = new ByteArrayOutputStream();
		return new PrintStream( out, true);
	}
	
}
