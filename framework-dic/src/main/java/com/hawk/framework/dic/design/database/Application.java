package com.hawk.framework.dic.design.database;

import java.util.List;

import com.hawk.framework.dic.design.IDictionaryObject;

/**
 * 表属于应用,应用属于schema
 * 
 * @author pzhang1
 *
 */
public class Application implements IDictionaryObject{



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Table> getTableList() {
		return tableList;
	}

	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;

	private String code;

	private String comment;

	private List<Table> tableList;
	
	private String id;

}
