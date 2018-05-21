package com.advancedpwr.action.i18n;


/**
*  @author Matthew Avery, mavery@advancedpwr.com on Apr 21, 2011
*  
*  Converts double-byte characters (e.g. Chinese) to HTML entities for display in a web browser.
*/
public class HtmlUnicoder
{
	protected String fieldSource;

	public HtmlUnicoder( String source )
	{
		setSource( source );
	}
	
	public String toEntities()
	{
		String result = "";
		String source = convertedSource();
		for ( int i = 0; i < source.length(); i++ )
		{
			if ( source.charAt( i ) > 127 )
			{
				result = result + "&#" + source.codePointAt( i ) + ";";
			}
			else
			{
				result += source.charAt( i );
			}
		}
		return result;
	}

	protected String convertedSource()
	{
		return getSource();
	}
	
	protected String getSource()
	{
		if ( fieldSource == null )
		{
			fieldSource = "";
		}
		return fieldSource;
	}

	protected void setSource( String source )
	{
		fieldSource = source;
	}
	
}
