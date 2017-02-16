package com.hawk.framework.dic.database;

import java.util.List;

public class Index {
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	private List<Column> columnList;
	
	
	private String name;

}
