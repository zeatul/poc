package com.hawk.codegen.database.convert;

import com.hawk.codegen.database.config.EnumDialect;

public class DomainConverterFactory {
	
	public static IDomainConverter build(EnumDialect dialect){
		ITypeConverter  typeConverter = null;
		if (dialect == EnumDialect.Mysql){
			typeConverter = new MysqlTypeConverter();
		}else if (dialect == EnumDialect.Oracle){
			typeConverter = new OracleTypeConverter();
		}else{
			throw new RuntimeException("Unsupport database type = "+ dialect.getValue());
		}
		
		return new DomainConverter(typeConverter);
	}

}
