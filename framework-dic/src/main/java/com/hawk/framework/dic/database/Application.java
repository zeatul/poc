package com.hawk.framework.dic.database;

import java.util.List;

/**
 * 表属于应用,应用属于schema
 * @author pzhang1
 *
 */
public class Application {
	
	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
	
	private List<Table> tableList;

}
