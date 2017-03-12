package com.hawk.framework.dic.design.database;

import com.hawk.framework.dic.design.IDictionaryObject;
import com.hawk.framework.dic.design.data.Word;

/**
 * 
 * @author pzhang1
 *
 */
public class Column implements IDictionaryObject{

	public Integer getNullable() {
		return nullable;
	}

	public void setNullable(Integer nullable) {
		this.nullable = nullable;
	}

	public Integer getIsPk() {
		return isPk;
	}

	public void setIsPk(Integer isPk) {
		this.isPk = isPk;
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

	public Word getDataDefinition() {
		return dataDefinition;
	}

	public void setDataDefinition(Word dataDefinition) {
		this.dataDefinition = dataDefinition;
	}

	private Word dataDefinition;
	
	/**
	 * 0=false,1=true
	 */
	private Integer nullable = 1;
	/**
	 * 0=false,1=true
	 */
	private Integer isPk = 0;
	
	
	
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
