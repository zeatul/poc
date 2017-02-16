package com.hawk.framework.codegen.database.parse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Table;

public class OracleDatabaseParser extends DatabaseParser {

	@Override
	protected void parseTable(Connection conn, Table table) throws Exception {
		String comment = "";
		String sql = "select table_name,comments from user_tab_comments where table_name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, table.getName());
			rs = ps.executeQuery();
			while(rs.next()){
				comment = rs.getString("comments");
				table.setComment(comment);
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
		String sql = "select column_name,comments from user_col_comments where table_name=?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,String> map = new HashMap<String,String>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, table.getName());
			rs = ps.executeQuery();
			while(rs.next()){
				String comment = rs.getString("comments");
				String name = rs.getString("column_name");
				map.put(name, comment);
			}
			rs.close();
			ps.close();
			for (Column column : table.getColumnList()){
				column.setComment(map.get(column.getName()));
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
		// TODO Auto-generated method stub
		
	}

}
