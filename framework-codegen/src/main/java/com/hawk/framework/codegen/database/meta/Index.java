package com.hawk.framework.codegen.database.meta;

import java.util.ArrayList;
import java.util.List;

public class Index {
	
	public int getUnique() {
		return unique;
	}

	public void setUnique(int unique) {
		this.unique = unique;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

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

	private List<Column> columnList = new ArrayList<Column>();
	
	private int unique = 0;
	private int pk = 0;
	
	private String name;
	
	

}
