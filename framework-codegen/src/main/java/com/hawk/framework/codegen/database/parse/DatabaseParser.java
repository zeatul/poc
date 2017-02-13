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
import com.hawk.framework.codegen.database.meta.Index;
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
			ResultSet tableRs = dm.getTables(null, dbConfig.getSchema(), dbConfig.getFilter(), types);

			while (tableRs.next()) {
				Table table = new Table();
				database.getTableList().add(table);
				/**
				 * 取表的基本信息
				 */
				table.setName(tableRs.getString("TABLE_NAME"));
				table.setComment(tableRs.getString("REMARKS"));
				table.setSchema(dbConfig.getSchema());
				
//				System.out.println("-------------table-------------");
//				int max = tableRs.getMetaData().getColumnCount();
//				for (int i=1; i<=max; i++){
//					System.out.println(tableRs.getMetaData().getColumnName(i)+":"+tableRs.getString(tableRs.getMetaData().getColumnName(i)));
//				}
				
				/**
				 * 取表的索引
				 */
				ResultSet indexRs = dm.getIndexInfo(null, table.getSchema(), table.getName(), false, false);
				while (indexRs.next()) {

				}
				
//				System.out.println("-------------index-------------");
//				max = indexRs.getMetaData().getColumnCount();
//				for (int i=1; i<=max; i++){
//					System.out.println(indexRs.getMetaData().getColumnName(i)+":"+indexRs.getString(indexRs.getMetaData().getColumnName(i)));
//				}
//				indexRs.close();

				/**
				 * 取表的主键
				 */
				ResultSet pkRs = dm.getPrimaryKeys(null, table.getSchema(), table.getName());
				Set<String> pkColumnNameSet = new HashSet<String>();
				while (pkRs.next()) {
					String pkColumnName = pkRs.getString("COLUMN_NAME");
					pkColumnNameSet.add(pkColumnName);
				}
				
				

				/**
				 * 取表的column
				 */
				ResultSet columnRs = dm.getColumns(null, "%", table.getName(), "%");
				List<Column> pkColumnList = new ArrayList<Column>();
				while (columnRs.next()) {
					Column column = new Column();
					table.getColumnList().add(column);
					column.setComment(columnRs.getString("REMARKS")); 
					column.setType(columnRs.getString("TYPE_NAME"));
					column.setNullable(1 == columnRs.getInt("NULLABLE"));
					column.setName(columnRs.getString("COLUMN_NAME"));

					if (pkColumnNameSet.contains(column.getName())) {
						column.setPk(true);
						pkColumnList.add(column);
					}

				}
				
				/**
				 * 设置主键索引
				 */
				if (pkColumnList.size() > 0){
					Index pk = new Index();
					pk.setUnique(true);
					pk.setPk(true);
					pk.setColumnList(pkColumnList);
					table.getIndexList().add(pk);
				}
				
//				System.out.println("-------------column-------------");
//				int max = columnRs.getMetaData().getColumnCount();
//				for (int i=1; i<=max; i++){
//					System.out.println(columnRs.getMetaData().getColumnName(i));
//				}
				columnRs.close();
			}
			tableRs.close();
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
