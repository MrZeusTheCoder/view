package com.advancedpwr.view.table;

import java.util.List;

public interface TableModel
{

	List<ColumnModel> columns();

	boolean nextRecord();

}
