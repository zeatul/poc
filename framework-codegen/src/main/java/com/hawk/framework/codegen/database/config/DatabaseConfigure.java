package com.hawk.framework.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfigure implements IDatabaseConfigure{
	
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public EnumDialect getDialect() {
		return dialect;
	}
	public void setDialect(EnumDialect dialect) {
		this.dialect = dialect;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	private String driver;
	private String url;
	private String user;
	private String password;
	private String schema;
	private EnumDialect dialect;
	private String filter;
	
	private DatabaseConfigure(){
		
	}
	
	public static IDatabaseConfigure build(){
		DatabaseConfigure databaseConfigure = new DatabaseConfigure();
		/**
		 * 读取配置文件
		 */
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(DatabaseConfigure.class.getResourceAsStream("database.properties"));
		
		try {
			props.load(in);
			databaseConfigure.setDriver(props.getProperty("driver"));
			databaseConfigure.setUrl(props.getProperty("url"));
			databaseConfigure.setUser(props.getProperty("user"));
			databaseConfigure.setPassword(props.getProperty("password"));
			databaseConfigure.setSchema(props.getProperty("schema"));
			databaseConfigure.setFilter(props.getProperty("filter"));
			databaseConfigure.setDialect(EnumDialect.parse(props.getProperty("dialect")));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return databaseConfigure;
	}

}
