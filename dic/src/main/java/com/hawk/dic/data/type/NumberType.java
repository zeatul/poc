package com.hawk.dic.data.type;

import java.math.BigDecimal;

/**
 * 精确数据类型
 * @author pzhang1
 *
 */
public class NumberType implements DataType {
	
	public Integer getS() {
		return s;
	}

	public void setS(Integer s) {
		this.s = s;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
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
	private Integer s;
	/**
	 * 小数精度
	 */
	private Integer p;
	
	/**
	 * 最小值
	 */
	private BigDecimal min;
	
	/**
	 * 最大值
	 */
	private BigDecimal max;

}
