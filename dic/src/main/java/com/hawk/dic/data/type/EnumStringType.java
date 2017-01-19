package com.hawk.dic.data.type;

/**
 * 字符串型枚举
 * @author pzhang1
 *
 */
public class EnumStringType extends EnumType<String>{

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
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