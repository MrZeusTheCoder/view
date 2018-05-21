/**
 * 
 */
package com.advancedpwr.view.filter;

import java.util.HashMap;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Mar 12, 2012
 *
 */
public class MimeTypeMap extends HashMap<String, String>
{
	/**
	 * 
	 */
	private static final String TEXT_HTML = "text/html";

	public MimeTypeMap()
	{
		put( ".html", TEXT_HTML );
		put( ".htm", TEXT_HTML );
		put( ".shtml", TEXT_HTML );
		put( ".css", "text/css");
		put( ".js", "text/javascript" );
		put( ".csv", "text/csv");
		put( ".xml", "text/xml");
		put( ".xls", "application/vnd.ms-excel");
		put( ".png", "image/png" );
		put( ".jpg", "image/jpeg" );
		put( ".jpeg", "image/jpeg");
		put( ".gif", "image/gif");
	}
	
	public String type( String inExtension )
	{
		String type = get( inExtension.toLowerCase() );
		if ( type == null )
		{
			return TEXT_HTML;
		}
		return type;
	}
	
}
