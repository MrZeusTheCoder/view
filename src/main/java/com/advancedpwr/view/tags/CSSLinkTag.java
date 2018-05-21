package com.advancedpwr.view.tags;


public class CSSLinkTag extends ScriptTag
{
	
	protected MediaAttribute fieldMedia;

	
	public Attribute media()
	{
		return getMediaAttribute(); 
	}


	public MediaAttribute getMediaAttribute()
	{
		if ( fieldMedia == null )
		{
			fieldMedia = new MediaAttribute();
		}
		return fieldMedia;
	}


	public void setMedia( String media )
	{
		getMediaAttribute().setValue( media );
	}

	protected Attribute sourceAttribute()
	{
		return new HrefAttribute();
	}
	
	protected Style createScript()
	{
		return new Style();
	}
	
	/**
	 * A friendly API alias for setSrc()
	 */
	public void setHref( Object href )
	{
		setSrc( href );
	}
	
	public Object href()
	{
		return src();
	}
}
