package com.hawk.framework.codegen.database.config;

import java.util.HashMap;
import java.util.Map;

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
	
	
    public static EnumDialect parse(String value){
    	EnumDialect[] a = EnumDialect.values();
    	Map<String,EnumDialect> m = new HashMap<String,EnumDialect>();
    	for (EnumDialect d : a){
    		m.put(d.getValue(), d);
    	}
    	
    	EnumDialect result = m.get(value);
    	
    	if (result == null)
    		throw new IllegalArgumentException("unmatched EnumDialect value = " + value);
    	
    	return result;
    }
	


}
