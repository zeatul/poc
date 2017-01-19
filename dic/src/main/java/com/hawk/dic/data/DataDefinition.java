package com.hawk.dic.data;

import com.hawk.dic.data.type.DataType;

/**
 * 数据定义
 * 名称
 * 类型
 * 描述
 * @author pzhang1
 *
 */
public class DataDefinition {
	
	/**
	 * 名称,唯一,按照数据库命名规则（小写,下划线分割）
	 */
	private String name;


	/**
	 * 类型
	 */
	private DataType dataType;
	
	/**
	 * 描述，描述需要支持多语言？
	 */
	private String desc;

}
