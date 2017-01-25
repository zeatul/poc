package com.hawk.codegen.database.meta;

/**
 * 一个domain对应一张表
 * @author pzhang1
 *
 */
public class Domain {
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	
	private Table table;
	
	/**
	 * 和table对应的表名
	 */
	private String name;
	
	private String module;

}
