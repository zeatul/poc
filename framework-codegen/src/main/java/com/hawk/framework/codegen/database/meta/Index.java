package com.hawk.framework.codegen.database.meta;

import java.util.ArrayList;
import java.util.List;

public class Index {
	
	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	private List<Column> columnList = new ArrayList<Column>();
	
	private boolean unique = false;
	private boolean pk = false;

}
