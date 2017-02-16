package com.hawk.framework.dic.data.type;

/**
 * 整型
 * @author pzhang1
 *
 */
public class IntegerType implements DataType{
	
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
		return DataType.EnumDataType.Integer;
	}

}
