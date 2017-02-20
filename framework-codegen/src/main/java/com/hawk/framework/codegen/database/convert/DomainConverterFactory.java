package com.hawk.framework.codegen.database.convert;

import com.hawk.framework.codegen.database.config.EnumDialect;

public class DomainConverterFactory {
	
	public static IDomainConverter build(EnumDialect dialect){
		
		
		return new DomainConverter(TypeConverterFactory.build(dialect));
	}

}
