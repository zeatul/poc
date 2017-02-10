package com.hawk.dic.data.type;

/**
 * 整型枚举
 * @author pzhang1
 *
 */
public class EnumIntegerType extends EnumType<Integer,String> {

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	private Integer min;
	
	private Integer max;

	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.EnumInteger;
	}
}
