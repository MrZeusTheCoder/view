package com.advancedpwr.action.controller;

import java.util.Date;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Nov 1, 2010
*/
public class TimeWindow
{
	public String end_date;
	
	/**
	 * Valid "window" in seconds.
	 * @param inWindow
	 */
	public void setWindow( long inWindow )
	{
		Date today = new Date();
		end_date = today.getTime() + inWindow * 1000 + "";
	}
	
	public boolean isWindowValid()
	{
		if ( end_date == null )
		{
			return false;
		}
		Date endDate = new Date( Long.parseLong( end_date ) );
		Date today = new Date();
		return today.before( endDate );
	}
}
