package com.advancedpwr.view.ui;

/**
*  @author Matthew Avery, mavery@advancedpwr.com on Sep 7, 2010
*
	<select title="required"
	class="gui-select required" id="revenue" name="revenue">
		<option value="" selected="selected"></option>
		<option value="100000" >&lt;$100k</option>
		<option value="1000000" >$100k - $1m</option>
		<option value="5000000" >$1m - $5m</option>
		<option value="10000000" >$5m+</option>
	</select>
*/
public class Option
{

	protected String fieldText;
	protected String fieldValue;
	protected boolean fieldSelected;

	public boolean isSelected()
	{
		return fieldSelected;
	}

	public void setSelected( boolean selected )
	{
		fieldSelected = selected;
	}

	public String getText()
	{
		return fieldText;
	}

	public String getValue()
	{
		return fieldValue;
	}

	public String selected()
	{
		if ( isSelected() )
		{
			return "selected=\"selected\"";
		}
		return "";
	}

	public Option setText( String text )
	{
		fieldText = text;
		return this;
	}

	public Option setValue( String value )
	{
		fieldValue = value;
		return this;
	}


}
