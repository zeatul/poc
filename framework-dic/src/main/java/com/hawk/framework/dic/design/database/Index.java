package com.hawk.framework.dic.design.database;

import java.util.List;

import com.hawk.framework.dic.design.IDictionaryObject;

public class Index implements IDictionaryObject{

	public Integer getIsPk() {
		return isPk;
	}

	public void setIsPk(Integer isPk) {
		this.isPk = isPk;
	}

	public Integer getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Integer isUnique) {
		this.isUnique = isUnique;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	private Integer isPk = 0;
	
	private Integer isUnique = 0;
	
	private String id;

}
