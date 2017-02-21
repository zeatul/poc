package com.hawk.framework.dic.data;

/**
 * 数据定义
 * 名称
 * 类型
 * 描述
 * @author pzhang1
 *
 */
public class DataDefinition {
	
	

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	

	public EnumDataType getDataType() {
		return dataType;
	}

	public void setDataType(EnumDataType dataType) {
		this.dataType = dataType;
	}

	public String getObjectLabel() {
		return objectLabel;
	}

	public void setObjectLabel(String objectLabel) {
		this.objectLabel = objectLabel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public Integer getCharMaxLength() {
		return charMaxLength;
	}

	public void setCharMaxLength(Integer charMaxLength) {
		this.charMaxLength = charMaxLength;
	}

	public Integer getCharMinLength() {
		return charMinLength;
	}

	public void setCharMinLength(Integer charMinLength) {
		this.charMinLength = charMinLength;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public Integer getDatetimePrecision() {
		return datetimePrecision;
	}

	public void setDatetimePrecision(Integer datetimePrecision) {
		this.datetimePrecision = datetimePrecision;
	}

	public Integer getNumericPrecision() {
		return numericPrecision;
	}

	public void setNumericPrecision(Integer numericPrecision) {
		this.numericPrecision = numericPrecision;
	}

	public Integer getNumericScale() {
		return numericScale;
	}

	public void setNumericScale(Integer numericScale) {
		this.numericScale = numericScale;
	}

	public Integer getIsEnum() {
		return isEnum;
	}

	public void setIsEnum(Integer isEnum) {
		this.isEnum = isEnum;
	}

	public String getEnumKey() {
		return enumKey;
	}

	public void setEnumKey(String enumKey) {
		this.enumKey = enumKey;
	}

	public String getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}

	/**
	 * 对象ID
	 */
	private String objectId;
	
	/**
	 * 用途，业务或技术
	 */
	private String useType;
	
	/**
	 * 数据类型
	 */
	private EnumDataType dataType;
	

	/**
	 * 对象标签
	 */
	private String objectLabel;
	

	/**
	 * 对象描述
	 */
	private String description;
	

	/**
	 * 对象名称
	 */
	private String objectName;
	
	
	/**
	 * 对象显示名称
	 */
	private String displayName;
	

	/**
	 * 正则表达式
	 */
	private String regex;
	

	/**
	 * 字符串的最大长度
	 */
	private Integer charMaxLength;
	

	/**
	 * 字符串的最小长度
	 */
	private Integer charMinLength;
	

	/**
	 * 最大值
	 */
	private String maxValue;
	

	/**
	 * 最小值
	 */
	private String minValue;
	
	/**
	 * 时间精度
	 */
	private Integer datetimePrecision;
	
	/**
	 * 数据精度
	 */
	private Integer numericPrecision;
	
	/**
	 * 数据小数精度
	 */
	private Integer numericScale;
	
	/**
	 * 是否是枚举
	 */
	private Integer isEnum = 0;
	
	/**
	 * 枚举值
	 */
	private String enumKey;
	
	/**
	 * 枚举显示值
	 */
	private String enumValue;

}
