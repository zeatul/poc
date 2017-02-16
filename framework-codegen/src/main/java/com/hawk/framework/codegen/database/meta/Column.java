package com.hawk.framework.codegen.database.meta;

public class Column {
	
	public int getNumericScale() {
		return numericScale;
	}
	public void setNumericScale(int numericScale) {
		this.numericScale = numericScale;
	}
	public int getNullable() {
		return nullable;
	}
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getCharMaxLength() {
		return charMaxLength;
	}
	public void setCharMaxLength(int charMaxLength) {
		this.charMaxLength = charMaxLength;
	}
	public int getNumericPrecision() {
		return numericPrecision;
	}
	public void setNumericPrecision(int numericPrecision) {
		this.numericPrecision = numericPrecision;
	}
	
	public int getDatetimePrecision() {
		return datetimePrecision;
	}
	public void setDatetimePrecision(int datetimePrecision) {
		this.datetimePrecision = datetimePrecision;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
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
	private String name;
	private String dataType;
	private String comment;
	/**
	 * 0=false,1=true
	 */
	private int nullable = 1;
	/**
	 * 0=false,1=true
	 */
	private int pk = 0;
	private int charMaxLength;
	private int numericPrecision;
	private int numericScale;
	private int datetimePrecision;
	private String columnType ;
	

	

}
