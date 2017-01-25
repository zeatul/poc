package com.hawk.codegen.database.config;

public class Configure extends AbstractProjectConfigure implements IDatabaseConfigure{
	
	
	
	
	
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

}
