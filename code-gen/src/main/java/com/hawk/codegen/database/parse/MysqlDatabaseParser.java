package com.hawk.codegen.database.parse;

import java.sql.Connection;

import com.hawk.codegen.database.meta.Table;

public class MysqlDatabaseParser extends DatabaseParser {

	@Override
	protected void parseTableComment(Connection conn, Table table) throws Exception {
		super.parseTableComment(conn,table);
	}

	@Override
	protected void parseColumnComment(Connection conn, Table table) throws Exception {
		super.parseColumnComment(conn, table);
	}

}
