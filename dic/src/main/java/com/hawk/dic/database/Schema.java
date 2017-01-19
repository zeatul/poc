package com.hawk.dic.database;

import java.util.List;

public class Schema {
	
	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	private List<Table> tableList; 

}
