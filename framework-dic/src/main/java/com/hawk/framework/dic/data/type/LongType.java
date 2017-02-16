package com.hawk.framework.dic.data.type;

/**
 * 长整型
 * 
 * @author pzhang1
 *
 */
public class LongType implements DataType {

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	private Long min;

	private Long max;
	
	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.Long;
	}
}
