package com.hawk.framework.codegen.database.config;

public enum EnumDialect {
	
	Mysql("mysql"),Oracle("oracle"),Sqlserver("sqlserver");
	
	public String getValue() {
		return value;
	}

	private String value;
	
	
	private EnumDialect(String value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
