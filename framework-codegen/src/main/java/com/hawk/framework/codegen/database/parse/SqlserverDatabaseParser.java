package com.hawk.framework.codegen.database.parse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Table;

public class SqlserverDatabaseParser extends DatabaseParser {

	@Override
	protected void parseTable(Connection conn, Table table) throws Exception {
		String comment = "";
		String sql = "select CONVERT(nvarchar(100), b.value) as comment from sys.tables a , sys.extended_properties b where a.object_id = b.major_id  and minor_id = 0 and a.name = '"+table.getCode()+"'";
		Statement stat=null;
		ResultSet rs=null;
		try{
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
				comment=rs.getString("comment");
			table.setComment(comment);
			rs.close();
			stat.close();
		} finally{
			try{
				conn.close();
			}catch(Exception e){
				
			}
		}		
	}

	@Override
	protected void parseColumn(Connection conn, Table table) throws Exception {
		
		
		
		Statement stat=null;
		ResultSet rs=null;
		try{
			for (Column column : table.getColumnList()){
				String comment = "";
				String sql = "select CONVERT(nvarchar(100), b.value) as comment from sys.tables a , sys.extended_properties b ,sys.all_columns c  where a.object_id = b.major_id and c.object_id = a.object_id and c.column_id = b.minor_id" 
						  + " and c.name = '"+column.getCode()+"' and a.name = '"+table.getCode()+"'"; 
				stat=conn.createStatement();
				rs=stat.executeQuery(sql);
				if(rs.next())
					comment=rs.getString("comment");
				column.setComment(comment);
				rs.close();
				stat.close();
			}
			
			
		} finally{
			try{
				conn.close();
			}catch(Exception e){
				
			}
		}
		
	}

	@Override
	protected void parseIndex(Connection conn, Table table) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
