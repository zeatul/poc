package com.hawk.framework.dic.database;

import com.hawk.framework.dic.data.DataDefinition;

/**
 * 
 * @author pzhang1
 *
 */
public class Column {

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

	public int getIsPk() {
		return isPk;
	}

	public void setIsPk(int isPk) {
		this.isPk = isPk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getNullable() {
		return nullable;
	}

	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	

	public DataDefinition getDataDefinition() {
		return dataDefinition;
	}

	public void setDataDefinition(DataDefinition dataDefinition) {
		this.dataDefinition = dataDefinition;
	}

	private DataDefinition dataDefinition;
	
	/**
	 * 0=false,1=true
	 */
	private int nullable = 1;
	/**
	 * 0=false,1=true
	 */
	private int isPk = 0;
	
	
	
	/**
	 * 数据库字段名,如果为空，则取dataDefinition的objectName
	 */
	private String name;
	
	/**
	 * 数据库字段名,如果为空，则取dataDefinition的objectCode
	 */
	private String code;
	
	/**
	 * 数据库字段描述,如果为空，则取dataDefinition的objectComment
	 */
	private String comment;
	
	/**
	 * 主键
	 */
	private String id;

}
