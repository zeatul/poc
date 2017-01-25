package com.hawk.codegen.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.codegen.database.config.IDatabaseConfigure;
import com.hawk.codegen.meta.Column;
import com.hawk.codegen.meta.Table;

public  class DatabaseParser {
	
	private static Logger logger = LoggerFactory.getLogger(DatabaseParser.class); 
	
	private IDatabaseConfigure config;
	
	public DatabaseParser(IDatabaseConfigure config){
		this.config = config;
	}
	
	
	
	public List<Table> parseDatabase() {
		Connection conn = null;
		Map<String,String> typeMap = new HashMap<String,String>();
		try {
			Class.forName(config.getDriver());
			conn = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
			DatabaseMetaData dm = conn.getMetaData();
			String[] types = { "TABLE", "VIEW" };
			ResultSet rsTable = dm.getTables(null, config.getSchema(), config.getFilter(), types);
			List<Table> tableList = new ArrayList<Table>();
			while(rsTable.next()){
				String tableName = rsTable.getString("TABLE_NAME");
				String tableComment = rsTable.getString("REMARKS");
				String tableSchema =rsTable.getString("TABLE_SCHEM");
				
				if (tableComment == null){
					tableComment = getTableCommentFromSQLServer2012(conn,tableName);
				}
				
				Table table = new Table();
				table.setName(tableName);
				table.setComment(tableComment);
				table.setSchema(tableSchema);
				
				tableList.add(table);
				
				/*pk*/
				Map<String,String>  mapPk = new HashMap<String,String>();
				ResultSet rsPk = dm.getPrimaryKeys(null, null, tableName);
				while(rsPk.next()){
					String pkColumnName = rsPk.getString("COLUMN_NAME");
					mapPk.put(pkColumnName,"");
				}
				rsPk.close();
				
				/*column*/
				ResultSet rsColumn = dm.getColumns(null, "%", tableName, "%"); 
				while(rsColumn.next()){
					String columnName = rsColumn.getString("COLUMN_NAME");
					String typeName = rsColumn.getString("TYPE_NAME");
					String columnComment = rsColumn.getString("REMARKS");
					int nullable = rsColumn.getInt("NULLABLE");
//					String xss = rsColumn.getString("DECIMAL_DIGITS");
//					String xyy = rsColumn.getString("COLUMN_SIZE") ;
					
					if (columnComment == null){
						columnComment = getColumnCommentFromSQLServer2012(conn,tableName,columnName);
					}
					
					Column column = new  Column();
					column.setComment(columnComment);
					column.setType(typeName);
					typeMap.put(typeName, typeName);
					column.setNullable(nullable == 1);
					column.setName(columnName);

					if (mapPk.get(columnName)!=null){
						column.setPk(true);
						table.getPrimaryKey().getColumns().add(column);
					}
					
					table.getColumns().add(column);
				}
				rsColumn.close();				
			}
			rsTable.close();
			
			Iterator<String> it =  typeMap.keySet().iterator();
			while(it.hasNext()){
				System.out.println (it.next());
			}
			System.out.println("tables count = "+tableList.size());
			
			return tableList;
		} catch (Exception e) {
			logger.error("parse error", e);
			throw new RuntimeException(e);
		} finally{
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				logger.error("parse error", e);
			}
		}

		

	}
	
	public String getTableCommentFromSQLServer2012(Connection conn,String tableName){
		String comment = "";
		String sql = "select CONVERT(nvarchar(100), b.value) as comment from sys.tables a , sys.extended_properties b where a.object_id = b.major_id  and minor_id = 0 and a.name = '"+tableName+"'";
		Statement stat=null;
		ResultSet rs=null;
		try{
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
				comment=rs.getString("comment");
		}catch(Exception e){
			logger.error("parse error",e);
			throw new RuntimeException(e);
		}finally{
			try{
				stat.close();
				rs.close();
			}catch(Exception e){
				
			}
		}
		return comment;
	}
	
	public String getColumnCommentFromSQLServer2012(Connection conn,String tableName,String columnName){
		String comment = "";
		String sql = "select CONVERT(nvarchar(100), b.value) as comment from sys.tables a , sys.extended_properties b ,sys.all_columns c  where a.object_id = b.major_id and c.object_id = a.object_id and c.column_id = b.minor_id" 
				  + " and c.name = '"+columnName+"' and a.name = '"+tableName+"'"; 
		Statement stat=null;
		ResultSet rs=null;
		try{
			stat=conn.createStatement();
			rs=stat.executeQuery(sql);
			if(rs.next())
				comment=rs.getString("comment");
		}catch(Exception e){
			logger.error("parse error",e);
			throw new RuntimeException(e);
		}finally{
			try{
				stat.close();
				rs.close();
			}catch(Exception e){
				
			}
		}
		return comment;
	}
	
	


}
