package com.hawk.framework.dic.database.converter;

import com.hawk.framework.dic.data.type.DataType;

/**
 * 将业务数据的数据类型转化成特定数据库的类型的字符串表示法
 * @author pzhang1
 *
 */
public interface TypeConverter {

	/**
	 * 将
	 * @param dataType
	 * @return
	 */
	public String convert(DataType dataType);
}
