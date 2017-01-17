package com.hawk.dic.data.type;

/**
 * 字符串类型
 * @author pzhang1
 *
 */
public class StringType implements DataType{
	
	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

		
	/**
	 * 最大长度
	 */
	private Integer maxLength ;
	
	/**
	 * 最小长度
	 */
	private Integer minLength ;

}
