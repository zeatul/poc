package com.hawk.dic.data;

import com.hawk.dic.data.type.DataType;

public class DataDefinition {
	
	/**
	 * id,唯一
	 */
	private String id;
	
	/**
	 * 名称,唯一
	 */
	private String name;
	
	/**
	 * 格式,日期类型数据,可能需要格式
	 */
	private String pattern;

	/**
	 * 类型
	 */
	private DataType dataType;
	
	/**
	 * 描述，描述需要支持多语言？
	 */
	private String desc;

}
