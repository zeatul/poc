package com.hawk.framework.codegen.database.config;

import java.util.Set;

import com.hawk.framework.dic.design.data.Word;

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
	public Word findWord(String code);
	
	/**
	 * 定义word的项目
	 * @return
	 */
	public String getWordProjectName();
	
	/**
	 * 定义word的package
	 * @return
	 */
	public String getWordPackage();
	
	/**
	 * 返回word定义文件的路径集合
	 * @return
	 */
	public Set<String> getWordFiles();
	
	

}
