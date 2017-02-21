package com.hawk.framework.dic.database;

import com.hawk.framework.dic.data.DataDefinition;

/**
 * 
 * @author pzhang1
 *
 */
public class Column {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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
	private int pk = 0;
	
	private String objectId;
	
	/**
	 * 数据库字段名
	 */
	private String name;

}
