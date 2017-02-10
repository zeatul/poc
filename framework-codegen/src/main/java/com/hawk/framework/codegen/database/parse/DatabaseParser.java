package com.hawk.framework.codegen.database.parse;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Table;

public abstract class DatabaseParser implements IDatabaseParser {

	public IDatabaseConfigure getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(IDatabaseConfigure dbConfig) {
		this.dbConfig = dbConfig;
	}

	private IDatabaseConfigure dbConfig;

	protected void parseTableComment(Connection conn, Table table) throws Exception{
		
	}

	protected void parseColumnComment(Connection conn, Table table) throws Exception{
		
	}

	private interface ParseComment {
		public void parse(Connection conn, Table table) throws Exception;
	}

	private Connection getConnection() throws Exception {
		Class.forName(dbConfig.getDriver());
		Connection conn = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
		return conn;
	}

	private void parseComment(Database database, final ParseComment parseComment) throws Throwable {
		if (database == null || database.getTableList() == null || database.getTableList().size() == 0)
			return;
		ExecutorService exec = Executors.newFixedThreadPool(10);

		List<Callable<Void>> tasks = new ArrayList<Callable<Void>>();

		class Task implements Callable<Void> {
			private Connection conn;
			private Table table;

			public Task(Connection conn, Table table) {
				this.conn = conn;
				this.table = table;
			}

			public Void call() throws Exception {
				parseComment.parse(conn, table);
				return null;
			}
		}

		for (Table table : database.getTableList()) {
			tasks.add(new Task(getConnection(), table));
		}
		List<Future<Void>> futures = exec.invokeAll(tasks);

		Iterator<Future<Void>> it = futures.iterator();

		for (@SuppressWarnings("unused")
		Future<Void> f : futures) {
			Future<Void> future = it.next();
			try {
				future.get();
			} catch (ExecutionException e) {
				throw e.getCause();
			}
		}

	}

	public Database parse() throws Throwable  {
		Connection conn = getConnection();
		Database database = new Database();
		try {
			DatabaseMetaData dm = conn.getMetaData();
			String[] types = { "TABLE", "VIEW" };
			ResultSet tablsRs = dm.getTables(null, dbConfig.getSchema(), dbConfig.getFilter(), types);

			while (tablsRs.next()) {
				Table table = new Table();
				database.getTableList().add(table);
				/**
				 * 取表的基本信息
				 */
				table.setName(tablsRs.getString("TABLE_NAME"));
				table.setComment(tablsRs.getString("REMARKS"));
				table.setSchema(tablsRs.getString("TABLE_SCHEM"));
				/**
				 * 取表的索引
				 */
				ResultSet indexRs = dm.getIndexInfo(null, table.getSchema(), table.getName(), false, false);
				while (indexRs.next()) {

				}
				indexRs.close();

				/**
				 * 取表的主键
				 */
				ResultSet pkRs = dm.getPrimaryKeys(null, table.getSchema(), table.getName());
				Set<String> pkColumnNameSet = new HashSet<String>();
				while (pkRs.next()) {
					String pkColumnName = pkRs.getString("COLUMN_NAME");
					pkColumnNameSet.add(pkColumnName);
				}
				pkRs.close();

				/**
				 * 取表的column
				 */
				ResultSet columnRs = dm.getColumns(null, "%", table.getName(), "%");
				while (columnRs.next()) {
					Column column = new Column();
					table.getColumnList().add(column);
					column.setComment(columnRs.getString("COLUMN_NAME"));
					column.setType(columnRs.getString("TYPE_NAME"));
					column.setNullable(1 == columnRs.getInt("NULLABLE"));
					column.setName(columnRs.getString("COLUMN_NAME"));

					if (pkColumnNameSet.contains(column.getName())) {
						column.setPk(true);
					}

				}
				columnRs.close();
			}
			tablsRs.close();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {

				}
			}
		}

		parseComment(database, new ParseComment() {
			public void parse(Connection conn, Table table) throws Exception {
				parseTableComment(conn, table);
			}
		});
		
		parseComment(database, new ParseComment() {
			public void parse(Connection conn, Table table) throws Exception {
				parseColumnComment(conn, table);
			}
		});
		
		return database;
	}

}
