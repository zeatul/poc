package com.hawk.dic.database;

import java.util.List;

public class Index {
	
	

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	private List<Column> columnList;

}
