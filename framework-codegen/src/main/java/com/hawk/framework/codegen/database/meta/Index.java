package com.hawk.framework.codegen.database.meta;

import java.util.ArrayList;
import java.util.List;

public class Index {
	
	public int getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(int isUnique) {
		this.isUnique = isUnique;
	}

	public int getIsPk() {
		return isPk;
	}

	public void setIsPk(int isPk) {
		this.isPk = isPk;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	private List<Column> columnList = new ArrayList<Column>();
	
	private int isUnique = 0;
	private int isPk = 0;
	
	private String code;
	
	

}
