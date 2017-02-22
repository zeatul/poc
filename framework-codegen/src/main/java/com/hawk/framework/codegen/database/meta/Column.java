package com.hawk.framework.codegen.database.meta;

public class Column {
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getIsPk() {
		return isPk;
	}
	public void setIsPk(int isPk) {
		this.isPk = isPk;
	}
	public int getCharMinLength() {
		return charMinLength;
	}
	public void setCharMinLength(int charMinLength) {
		this.charMinLength = charMinLength;
	}
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
	
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	private String code;
	private String dataType;
	private String comment;
	/**
	 * 0=false,1=true
	 */
	private int nullable = 1;
	/**
	 * 0=false,1=true
	 */
	private int isPk = 0;
	private int charMaxLength;
	private int charMinLength;
	private int numericPrecision;
	private int numericScale;
	private int datetimePrecision;
	private String columnType ;
	

	

}
