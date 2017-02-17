package com.hawk.framework.dic.data.type;

import java.math.BigDecimal;

/**
 * 精确数据类型
 * @author pzhang1
 *
 */
public class NumericType implements DataType {
	
	

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	

	public BigDecimal getMin() {
		return min;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMax() {
		return max;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	/**
	 * 整个精度
	 */
	private Integer precision;
	/**
	 * 小数精度
	 */
	private Integer scale;
	
	/**
	 * 最小值
	 */
	private BigDecimal min;
	
	/**
	 * 最大值
	 */
	private BigDecimal max;
	
	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.Numeric;
	}

}
