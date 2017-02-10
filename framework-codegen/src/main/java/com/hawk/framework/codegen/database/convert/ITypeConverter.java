package com.hawk.framework.codegen.database.convert;

public interface ITypeConverter {
	
	public String convertFromDbToJava(String dbType);
	
	public String convertFromDbToJdbc(String dbType);

}
