package com.hawk.framework.codegen.database.convert;

import java.util.HashMap;
import java.util.Map;

public class MysqlTypeConverter extends TypeConverter {

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
		javaTypeMap.put("varchar","String");
		javaTypeMap.put("char","String");
		javaTypeMap.put("nvarchar","String");
		javaTypeMap.put("decimal", "BigDecimal");
		javaTypeMap.put("tinyint", "Short");
		javaTypeMap.put("bigint", "Long");
		javaTypeMap.put("timestamp","Date");
		
		
		jdbcTypeMap.put("int", "numeric");
		jdbcTypeMap.put("int identity", "numeric");
		jdbcTypeMap.put("datetime", "timestamp");
		jdbcTypeMap.put("timestamp", "timestamp");
		jdbcTypeMap.put("varchar", "varchar");
		jdbcTypeMap.put("char", "varchar");
		jdbcTypeMap.put("nvarchar", "varchar");
		jdbcTypeMap.put("decimal", "numeric");
		jdbcTypeMap.put("bigint", "numeric");
		jdbcTypeMap.put("clob", "longvarchar");
		jdbcTypeMap.put("blob", "varbinary");
	}

	

}
