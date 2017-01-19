package com.hawk.dic.database;


import java.util.List;

public class Table {
	
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
	
	 

}
