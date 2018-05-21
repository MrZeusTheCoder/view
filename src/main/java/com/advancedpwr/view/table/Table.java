package com.advancedpwr.view.table;

public abstract class Table
{
	public abstract void newRow();
	
	public abstract void headerCell( Object name );
	
	public abstract void newCell( Object value );
	
	public void fill( TableModel data )
	{
		newRow();
		for ( ColumnModel column : data.columns() )
		{
			headerCell( column.getName() );
		}
		
		while ( data.nextRecord() )
		{
			newRow();
			for ( ColumnModel column : data.columns() )
			{
				newCell( column.getValue() );
			}
		}

	}

}
