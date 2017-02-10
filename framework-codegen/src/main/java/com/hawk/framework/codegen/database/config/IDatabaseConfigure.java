package com.hawk.framework.codegen.database.config;

public interface IDatabaseConfigure {
	public String getDriver();
	public String getUrl();
	public String getUser();
	public String getPassword();
	public String getSchema();
	public String getFilter();
	public EnumDialect getDialect();
}
