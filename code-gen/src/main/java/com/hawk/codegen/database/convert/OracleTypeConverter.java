package com.hawk.codegen.database.convert;

import java.util.HashMap;
import java.util.Map;

public class OracleTypeConverter extends TypeConverter{

	@Override
	protected Map<String, String> getJavaTypeMap() {
		return javaTypeMap;
	}

	@Override
	protected Map<String, String> getJdbcTypeMap() {
		return jdbcTypeMap;
	}
	
	private static Map<String,String> javaTypeMap = new HashMap<String,String>();
	private static Map<String,String> jdbcTypeMap = new HashMap<String,String>();
	
	static{
		javaTypeMap.put("int","Integer");
		javaTypeMap.put("int identity","Integer");
		javaTypeMap.put("datetime","Date");
		javaTypeMap.put("date","Date");
		javaTypeMap.put("timestamp(6)","Date");
		javaTypeMap.put("timestamp(3)","Date");
		javaTypeMap.put("varchar","String");
		javaTypeMap.put("varchar2","String");
		javaTypeMap.put("char","String");
		javaTypeMap.put("decimal", "BigDecimal");
		javaTypeMap.put("long", "Long");
		javaTypeMap.put("clob", "String");
		javaTypeMap.put("blob","byte[]");
		
		
		jdbcTypeMap.put("int", "numeric");
		jdbcTypeMap.put("int identity", "numeric");
		jdbcTypeMap.put("datetime", "timestamp");
		jdbcTypeMap.put("date", "timestamp");
		jdbcTypeMap.put("timestamp(6)", "timestamp");
		jdbcTypeMap.put("timestamp(3)", "timestamp");
		jdbcTypeMap.put("varchar", "varchar");
		jdbcTypeMap.put("char", "varchar");
		jdbcTypeMap.put("decimal", "numeric");
		jdbcTypeMap.put("long", "numeric");
		jdbcTypeMap.put("clob", "longvarchar");
		jdbcTypeMap.put("blob", "varbinary");
	}

}
