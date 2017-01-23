package com.hawk.dic.data.type;

/**
 * 字符串类型
 * @author pzhang1
 *
 */
public class StringType implements DataType{
	
	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

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
	
	/**
	 * 正则
	 */
	private String regex;
	
	@Override
	public EnumDataType getType() {
		return DataType.EnumDataType.String;
	}

}
