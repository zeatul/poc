package com.hawk.framework.dic.design.database;

import java.util.List;

public class Index {

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsPk() {
		return isPk;
	}

	public void setIsPk(int isPk) {
		this.isPk = isPk;
	}

	public int getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(int isUnique) {
		this.isUnique = isUnique;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	private List<Column> columnList;

	private String name;

	private String code;

	private String comment;
	
	private int isPk = 0;
	
	private int isUnique = 0;
	
	private String id;

}
