package com.hawk.framework.dic.data.type;

/**
 * 日期类型
 * @author pzhang1
 *
 */
public class DateType implements DataType{

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.Date;
	}
	
	private int precision;

}
