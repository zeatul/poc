package com.hawk.dic.database.type.converter;

import com.hawk.dic.data.type.DataType;

public interface TypeConverter {

	/**
	 * 将
	 * @param dataType
	 * @return
	 */
	public String convert(DataType dataType);
}
