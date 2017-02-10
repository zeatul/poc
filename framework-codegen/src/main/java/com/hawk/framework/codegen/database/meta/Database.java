package com.hawk.framework.codegen.database.meta;

import java.util.ArrayList;
import java.util.List;

public class Database {
	
	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	private List<Table> tableList = new ArrayList<Table>();

}
