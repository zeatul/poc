package com.hawk.framework.codegen.database.meta;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<Column> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}
	public List<Index> getIndexList() {
		return indexList;
	}
	public void setIndexList(List<Index> indexList) {
		this.indexList = indexList;
	}
	private String code;
	private String comment;
	private String schema;
	private List<Column> columnList = new ArrayList<Column>();
	private List<Index> indexList = new ArrayList<Index>();
	

}
