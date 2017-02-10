package com.hawk.framework.codegen.database.convert;

import java.util.Map;

public abstract class TypeConverter implements ITypeConverter{
	
	protected abstract Map<String,String> getJavaTypeMap() ;
	protected abstract Map<String,String> getJdbcTypeMap() ;
	

	public String convertFromDbToJava(String dbType) {
		String javaType = getJavaTypeMap().get(dbType.toLowerCase());
		if(javaType == null)
			throw new RuntimeException("Unknown Db Type ="+dbType);
		return javaType;
	}

	public String convertFromDbToJdbc(String dbType) {
		String jdbcType = getJdbcTypeMap().get(dbType.toLowerCase());
		if(jdbcType == null)
			throw new RuntimeException("Unknown Db Type ="+dbType);
		return jdbcType;
	}

}
