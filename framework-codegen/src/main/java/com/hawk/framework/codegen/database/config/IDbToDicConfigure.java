package com.hawk.framework.codegen.database.config;

public interface IDbToDicConfigure {
	
	/**
	 *  根据数据库表字段的名称分析是否属于同一个字段类型定义
	 *  名称完全相同的是，
	 *  名称结尾相同的是，
	 * @param columnName
	 * @return
	 */
	public String computeDataDefinitionName(String columnName);

}
