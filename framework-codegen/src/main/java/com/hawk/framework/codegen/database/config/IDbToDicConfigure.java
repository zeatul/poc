package com.hawk.framework.codegen.database.config;

public interface IDbToDicConfigure {
	
	/**
	 * 查找columnName的同义词
	 * @param columnName
	 * @return
	 */
	public String findSynonymCode(String columnName);
	
	/**
	 * 查找code的id
	 * @param code
	 * @return
	 */
	public String findId(String code);

}
