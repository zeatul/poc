package com.hawk.codegen.database.convert;

public interface ITypeConverter {
	
	public String convertFromDbToJava(String dbType);
	
	public String convertFromDbToJdbc(String dbType);

}
