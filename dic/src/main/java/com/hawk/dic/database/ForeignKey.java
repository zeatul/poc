package com.hawk.dic.database;

import java.util.List;

public class ForeignKey {
	
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

}
