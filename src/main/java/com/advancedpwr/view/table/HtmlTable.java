package com.advancedpwr.view.table;

import java.util.ArrayList;
import java.util.List;

public class HtmlTable extends Table
{
	protected List fieldRows;
	protected HtmlRow fieldCurrentRow;
	protected boolean on;
	
	
	public List getRows()
	{
		if ( fieldRows == null )
		{
			fieldRows = new ArrayList();
		}

		return fieldRows;
	}
	

	public Object rows()
	{
		return getRows();
	}


	public void newRow()
	{
		HtmlRow row = new HtmlRow();
		if ( on )
		{
			row.setCssClass( "rowOn" );
		}
		else
		{
			row.setCssClass( "rowOff" );
		}
		on = !on;
		getRows().add( row );
		setCurrentRow( row );
	}


	public void headerCell( Object string )
	{
		HeaderCell cell = new HeaderCell();
		cell.setValue( string );
		getCurrentRow().add( cell );
	}


	public void newCell( Object value )
	{
		HtmlCell cell = new HtmlCell();
		cell.setValue( value );
		getCurrentRow().add( cell );
	}

	public HtmlRow getCurrentRow()
	{
		return fieldCurrentRow;
	}

	public void setCurrentRow( HtmlRow currentRow )
	{
		fieldCurrentRow = currentRow;
	}

}
