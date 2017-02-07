package com.hawk.codegen.database.meta;

/**
 * 映射单个字段
 * @author pzhang1
 *
 */
public class Field {
	
	public String getFieldJdbcType() {
		return fieldJdbcType;
	}
	public void setFieldJdbcType(String fieldJdbcType) {
		this.fieldJdbcType = fieldJdbcType;
	}
	public String getDbtype() {
		return dbtype;
	}
	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	/**
	 * java
	 */
	private String fieldName;	
	private String fieldType;
	private String fieldDesc;
	private String fieldJdbcType;
	
	/**
	 * db
	 */
	private String columnName;
	private String dbtype;

}
