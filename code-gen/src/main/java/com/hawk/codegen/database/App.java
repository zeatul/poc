package com.hawk.codegen.database;

import com.hawk.codegen.database.config.DatabaseConfigure;
import com.hawk.codegen.database.config.IDatabaseConfigure;
import com.hawk.codegen.database.config.IProjectConfigure;
import com.hawk.codegen.database.config.ProjectConfigure;
import com.hawk.codegen.database.meta.Database;
import com.hawk.codegen.database.parse.DatabaseParserFactory;
import com.hawk.codegen.database.parse.IDatabaseParser;

public class App {
	
	public static void main(String[] args) throws Throwable{
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build();
		IProjectConfigure projectConfigure = ProjectConfigure.build();
		
		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);
		
		Database database = dbParser.parse();
	}

}
