package com.hawk.framework.dic.data.type;

/**
 * 日期类型
 * @author pzhang1
 *
 */
public class DateType implements DataType{

	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.Date;
	}

}
