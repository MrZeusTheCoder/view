package com.advancedpwr.view.tags;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.advancedpwr.view.render.ResourceViewRenderer;

import junit.framework.TestCase;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Oct 14, 2010
*/
public class ScriptTagTest extends TestCase
{

	protected void setUp() throws Exception
	{
		super.setUp();
	}

	public void testSrc() throws Exception
	{
		ScriptTag tag = new ScriptTag();
		tag.setSrc( "popup.js" );
		assertEquals( "<script type=\"text/javascript\" src=\"popup.js\"></script>", render( tag ) );
	}
	
	public void testSrc_app_path() throws Exception
	{
		ScriptTag tag = new ScriptTag();
		tag.setSrc( "popup.js" );
		tag.setApplicationPath( "/jilda" );
		assertEquals( "<script type=\"text/javascript\" src=\"/jilda/popup.js\"></script>", render( tag ) );
	}
	
	public void testSrc_app_path_object() throws Exception
	{
		ScriptTag tag = new ScriptTag();
		tag.setSrc( tag);
		tag.setApplicationPath( "/jilda" );
		assertEquals( "<script type=\"text/javascript\" src=\"/jilda/com.advancedpwr.view.tags.ScriptTag.js\"></script>", render( tag ) );
	}
	
	public void testElements() throws Exception
	{
		ScriptTag tag = new ScriptTag();
		tag.add( "function aFunction()\n" +
				"{\n" +
				"	void(0)\n" +
				"}\n");
		assertEquals( "<script type=\"text/javascript\" >\n" +  
				"function aFunction()\n" + 
				"{\n" + 
				"	void(0)\n" + 
				"}\n" + 
				"</script>", render( tag ) );
	}
	
	protected String render( ScriptTag tag ) throws Exception
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream( out );
		ResourceViewRenderer renderer = new ResourceViewRenderer();
		renderer.setPrintStream( stream );
		renderer.setExtension( ".html" );
		renderer.setView( tag );
		renderer.render();
		return out.toString();
	}
	
	public void testIsScript() 
	{
		ScriptTag tag = new ScriptTag();
		
		assertFalse(tag.isScript(null));
	}
}
