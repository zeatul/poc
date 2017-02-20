package com.hawk.framework.codegen.database.convert;

import com.hawk.framework.codegen.database.config.EnumDialect;

public class TypeConverterFactory {
	
	public static ITypeConverter build(EnumDialect dialect){
		ITypeConverter  typeConverter = null;
		if (dialect == EnumDialect.Mysql){
			typeConverter = new MysqlTypeConverter();
		}else if (dialect == EnumDialect.Oracle){
			typeConverter = new OracleTypeConverter();
		}else{
			throw new RuntimeException("Unsupport database type = "+ dialect.getValue());
		}
		return typeConverter;
	}

}
