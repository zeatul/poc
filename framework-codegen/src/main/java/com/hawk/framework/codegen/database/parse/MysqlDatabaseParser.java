package com.hawk.framework.codegen.database.parse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Index;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.utils.DatabaseTools;

public class MysqlDatabaseParser extends DatabaseParser {

	@Override
	protected void parseTable(Connection conn, Table table) throws Exception {
		
		String sql = "select table_schema, table_name,table_comment from information_schema.tables where table_schema = ? and table_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, table.getSchema());
			ps.setString(2, table.getCode());
			rs = ps.executeQuery();
			while(rs.next()){
				table.setComment(rs.getString("table_comment"));
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}	
		
		
	}

	@Override
	protected void parseColumn(Connection conn, Table table) throws Exception {
		String sql = "select column_name,is_nullable,data_type,character_maximum_length,numeric_precision,numeric_scale,datetime_precision ,column_type,column_key,column_comment from information_schema.columns where table_schema = ? and table_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, table.getSchema());
			ps.setString(2, table.getCode());
			rs = ps.executeQuery();
			while(rs.next()){
				Column column= new Column();
				table.getColumnList().add(column);
				column.setCode(rs.getString("column_name"));
				column.setNullable("yes".equalsIgnoreCase(rs.getString("is_nullable"))?1:0);
				column.setIsPk("pri".equalsIgnoreCase(rs.getString("column_key"))?1:0);
				column.setDataType(rs.getString("data_type"));
				column.setComment(rs.getString("column_comment"));
				column.setColumnType(rs.getString("column_type"));
				column.setCharMaxLength(rs.getInt("character_maximum_length"));
				column.setNumericPrecision(rs.getInt("numeric_precision"));
				column.setNumericScale(rs.getInt("numeric_scale"));
				column.setDatetimePrecision(rs.getInt("datetime_precision"));				
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	@Override
	protected void parseIndex(Connection conn, Table table) throws Exception {
		String sql = "select constraint_name,table_name,column_name from information_schema.key_column_usage where table_schema = ? and table_name = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,Column> columnMap = DatabaseTools.convert(table.getColumnList());
		Map<String,Index> indexMap = new HashMap<String,Index>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, table.getSchema());
			ps.setString(2, table.getCode());
			rs = ps.executeQuery();
			while(rs.next()){
				String indexName = rs.getString("constraint_name");
				String columnName = rs.getString("column_name");
				Index index = indexMap.get("indexName");
				Column column = columnMap.get(columnName);
				int isPk = "primary".equalsIgnoreCase(indexName)?1:0;
				if (column == null)
					throw new RuntimeException("unknown index column = "+columnName);
				if (index == null){
					index = new Index();
					index.setCode(indexName);
					indexMap.put(indexName, index);
					index.setIsUnique(1);
					index.setIsPk(isPk);
					table.getIndexList().add(index);
				}
				column.setIsPk(isPk);
				index.getColumnList().add(column);				
				
			}
		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}	
	}

}
