package com.hawk.codegen.database.parse;

import com.hawk.codegen.database.config.EnumDialect;
import com.hawk.codegen.database.config.IDatabaseConfigure;

public class DatabaseParserFactory {
	
	public static IDatabaseParser build(IDatabaseConfigure databaseConfigure){
		DatabaseParser parser = null;
		if (databaseConfigure.getDialect() == EnumDialect.Mysql){
			parser = new MysqlDatabaseParser();			
		}else if (databaseConfigure.getDialect() == EnumDialect.Oracle){
			parser = new OracleDatabaseParser();			
		}else if (databaseConfigure.getDialect() == EnumDialect.Sqlserver){
			parser = new SqlserverDatabaseParser();			
		}else{
			throw new RuntimeException("Unsupported dialect = " + databaseConfigure.getDialect().getValue());
		}
		
		parser.setDbConfig(databaseConfigure);
		return parser;
	}

}
