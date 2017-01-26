package com.hawk.codegen.database.meta;

import java.util.List;

/**
 * 映射单表
 * @author pzhang1
 *
 */
public class Domain {
	


	

	public List<Field> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<Field> keyList) {
		this.keyList = keyList;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<String> getImportList() {
		return importList;
	}

	public void setImportList(List<String> importList) {
		this.importList = importList;
	}

	public List<Field> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<Field> fieldList) {
		this.fieldList = fieldList;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * java
	 */
	private List<Field> fieldList;	
	private String className;
	private List<String> importList; //class import的类
	private String packageName; //class所在的package
	private String desc;
	/**
	 * db
	 */
	private String tableName; //class 对应的表名
	private List<Field> keyList; //主键

}
