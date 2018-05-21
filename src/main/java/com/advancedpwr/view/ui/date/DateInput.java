/**
 * 
 */
package com.advancedpwr.view.ui.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.advancedpwr.view.tags.CSSLinkTag;
import com.advancedpwr.view.tags.ImageTag;
import com.advancedpwr.view.tags.ScriptTag;

/**
 * @author Matthew Avery, mavery@advancedpwr.com
 * Created: Jan 10, 2013
 *
 */
public class DateInput
{

	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat( "MM/dd/yyyy" );
	
	protected Date fieldDate;
	
	public Object css()
	{
		CSSLinkTag tag = new CSSLinkTag();
		tag.setSrc( DatePicker.class.getName() + ".css" );
		return tag;
	}
	
	public Object scripts()
	{
		ScriptTag tag = new ScriptTag();
		tag.setSrc( new DatePicker() );
		return tag;
	}
	
	public Object name()
	{
		return getClass().getSimpleName();
	}

	public Date getDate()
	{
		if ( fieldDate == null )
		{
			fieldDate = new Date();
		}
		return fieldDate;
	}

	public String formattedDate()
	{
		return INPUT_DATE_FORMAT.format( getDate() );
	}
	
	public void setDate( Date date )
	{
		fieldDate = date;
	}
	
	public void setDate( String inDate )
	{
		try
		{
			setDate( INPUT_DATE_FORMAT.parse( inDate ) );
		}
		catch ( ParseException e )
		{
			throw new RuntimeException(e);
		}
	}
	
	public Object imageTag()
	{
		ImageTag imageTag = new ImageTag();
		imageTag.setSrc( new CalendarIcon() );
		return imageTag;
	}

}
