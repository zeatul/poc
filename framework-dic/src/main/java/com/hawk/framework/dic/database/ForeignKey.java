package com.hawk.framework.dic.database;

import java.util.List;

public class ForeignKey {
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<ColumnPair> getColumnPairList() {
		return columnPairList;
	}

	public void setColumnPairList(List<ColumnPair> columnPairList) {
		this.columnPairList = columnPairList;
	}

	public static class ColumnPair{
		public Column getParentColumn() {
			return parentColumn;
		}
		public void setParentColumn(Column parentColumn) {
			this.parentColumn = parentColumn;
		}
		public Column getChildColumn() {
			return childColumn;
		}
		public void setChildColumn(Column childColumn) {
			this.childColumn = childColumn;
		}
		private Column parentColumn;
		private Column childColumn;
	}
	
	public Table getParentTable() {
		return parentTable;
	}

	public void setParentTable(Table parentTable) {
		this.parentTable = parentTable;
	}

	public Table getChildTable() {
		return childTable;
	}

	public void setChildTable(Table childTable) {
		this.childTable = childTable;
	}

	private Table parentTable;
	
	private Table childTable;
	
	private List<ColumnPair> columnPairList ;
	
	private String code;
	private String name;
	private String comment;

}
