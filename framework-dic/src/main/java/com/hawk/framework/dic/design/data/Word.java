package com.hawk.framework.dic.design.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hawk.framework.dic.design.IDictionaryObject;
import com.hawk.framework.utility.tools.CollectionTools;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 数据定义 名称 类型 描述
 * 
 * @author pzhang1
 *
 */
public class Word implements IDictionaryObject {

	

	public String getExtraValidation() {
		return extraValidation;
	}

	public void setExtraValidation(String extraValidation) {
		this.extraValidation = extraValidation;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPatternComment() {
		return patternComment;
	}

	public void setPatternComment(String patternComment) {
		this.patternComment = patternComment;
	}

	public Map<String, String> getEnumValues() {
		return enumValues;
	}

	public void setEnumValues(Map<String, String> enumValues) {
		this.enumValues = enumValues;
	}

	public Integer getIsOnlyAscii() {
		return isOnlyAscii;
	}

	public void setIsOnlyAscii(Integer isOnlyAscii) {
		this.isOnlyAscii = isOnlyAscii;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public EnumDataType getDataType() {
		return dataType;
	}

	public void setDataType(EnumDataType dataType) {
		this.dataType = dataType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	/**
	 * 对象ID
	 */
	private String id;

	/**
	 * 用途，业务或技术
	 */
	private String useType;

	/**
	 * 数据类型
	 */
	private EnumDataType dataType;

	/**
	 * 对象编码
	 */
	private String code;

	/**
	 * 对象描述
	 */
	private String comment;

	/**
	 * 对象名称
	 */
	private String name;

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
	 * 是否只包含一个字节长度的字符
	 */
	private Integer isOnlyAscii;

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
	private Map<String, String> enumValues;
	
	/**
	 * 格式描述
	 */
	private String patternComment ;
	
	/**
	 * 格式
	 */
	private String pattern;
	
	/**
	 * 扩展校验
	 */
	private String extraValidation;

	public void setEnumValues(String enumValuesStr) {
		if (StringTools.isNullOrEmpty(enumValuesStr))
			return;

		if (this.enumValues == null) {
			this.enumValues = new HashMap<String, String>();
		}

		String[] pairs = enumValuesStr.split(";");

		for (String pair : pairs) {
			String[] str = pair.split(":");
			this.enumValues.put(str[0], str.length > 1 ? str[1] : null);
		}
	}

	public String getEnumValuesAsString() {
		if (CollectionTools.isNullOrEmpty(this.enumValues)) {
			return null;
		}

		List<String> list = new ArrayList<String>();
		this.enumValues.forEach((key, value) -> {
			if (StringTools.isNullOrEmpty(value)){
				list.add(key);
			}else{
				list.add(StringTools.concatWithSymbol(":", key,value));
			}
			
		});
		
		return StringTools.concatWithSymbol(";",list);

	}
}
