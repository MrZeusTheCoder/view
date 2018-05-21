package com.advancedpwr.view.tags;

import com.advancedpwr.view.render.AbstractRendererTest;

public class CSSLinkTagTest extends AbstractRendererTest
{

	public void testCSSLinkTag()
	{
		CSSLinkTag tag = new CSSLinkTag();
		tag.setSrc( "styles/style.css" );
		renderer.render(tag);
		
		assertOutput( "<link rel=\"stylesheet\" type=\"text/css\"  href=\"styles/style.css\" />" );
	}
	
	public void testCSSLinkTag_resource()
	{
		CSSLinkTag tag = new CSSLinkTag();
		tag.setSrc( new MyLook() );
		tag.setMedia( "screen" );
		renderer.render(tag);
		
		assertOutput( "<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"com.advancedpwr.view.tags.MyLook.css\" />" );
	}
	
	public void testCSSLinkTag_resource_w_path()
	{
		CSSLinkTag tag = new CSSLinkTag();
		tag.setHref( new MyLook() );
		tag.setMedia( "screen" );
		tag.setApplicationPath( "/myapp" );
		renderer.render(tag);
		
		assertOutput( "<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"/myapp/com.advancedpwr.view.tags.MyLook.css\" />" );
	}
}
