package com.hawk.framework.dic.database;


import java.util.List;

public class Table {
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Column> getCloumnList() {
		return cloumnList;
	}

	public void setCloumnList(List<Column> cloumnList) {
		this.cloumnList = cloumnList;
	}

	public List<Index> getIndexList() {
		return indexList;
	}

	public void setIndexList(List<Index> indexList) {
		this.indexList = indexList;
	}

	private List<Column> cloumnList;
	
	private List<Index> indexList;
	
	private String name;
	
	/**
	 * 表类型(普通表,树表)
	 */
	private String type; 

}
