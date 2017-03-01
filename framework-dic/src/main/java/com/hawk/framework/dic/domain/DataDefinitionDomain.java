package com.hawk.framework.dic.domain;
import java.io.Serializable;




/**
 * 数据字典定义
 * The class is mapped to the table t_dic_data_definition 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class DataDefinitionDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 用途类型 use_type
	 */
	private String useType;
	
	/**
	 * 数据类型 data_type
	 */
	private String dataType;
	
	/**
	 * 编码 object_code
	 */
	private String objectCode;
	
	/**
	 * 中文名 object_name
	 */
	private String objectName;
	
	/**
	 * 描述 object_comment
	 */
	private String objectComment;
	
	/**
	 * 显示名称 object_display_name
	 */
	private String objectDisplayName;
	
	/**
	 * 正则表达式 regex
	 */
	private String regex;
	
	/**
	 * 最大长度 char_max_length
	 */
	private Integer charMaxLength;
	
	/**
	 * 最小长度 char_min_length
	 */
	private Integer charMinLength;
	
	/**
	 * 最大值 max_value
	 */
	private String maxValue;
	
	/**
	 * 最小值 min_value
	 */
	private String minValue;
	
	/**
	 * 时间精度 datetime_precision
	 */
	private Integer datetimePrecision;
	
	/**
	 * 数据精度 numeric_precision
	 */
	private Integer numericPrecision;
	
	/**
	 * 数据小数精度 numeric_scale
	 */
	private Integer numericScale;
	
	/**
	 * 是否枚举(yes/no) is_enum
	 */
	private Integer isEnum;
	
	/**
	 * 枚举值 enum_key
	 */
	private String enumKey;
	
	/**
	 * 枚举显示值 enum_value
	 */
	private String enumValue;
	
	
	/**
	 * 
	 * @return 对象ID object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 对象ID object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 用途类型 use_type
	 */
	public String getUseType(){
		return useType;
	}
	
	/**
	 * 
	 * @param useType 用途类型 use_type
	 */	
	public void setUseType (String useType) {
		this.useType = useType;
	}
	
	/**
	 * 
	 * @return 数据类型 data_type
	 */
	public String getDataType(){
		return dataType;
	}
	
	/**
	 * 
	 * @param dataType 数据类型 data_type
	 */	
	public void setDataType (String dataType) {
		this.dataType = dataType;
	}
	
	/**
	 * 
	 * @return 编码 object_code
	 */
	public String getObjectCode(){
		return objectCode;
	}
	
	/**
	 * 
	 * @param objectCode 编码 object_code
	 */	
	public void setObjectCode (String objectCode) {
		this.objectCode = objectCode;
	}
	
	/**
	 * 
	 * @return 中文名 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 中文名 object_name
	 */	
	public void setObjectName (String objectName) {
		this.objectName = objectName;
	}
	
	/**
	 * 
	 * @return 描述 object_comment
	 */
	public String getObjectComment(){
		return objectComment;
	}
	
	/**
	 * 
	 * @param objectComment 描述 object_comment
	 */	
	public void setObjectComment (String objectComment) {
		this.objectComment = objectComment;
	}
	
	/**
	 * 
	 * @return 显示名称 object_display_name
	 */
	public String getObjectDisplayName(){
		return objectDisplayName;
	}
	
	/**
	 * 
	 * @param objectDisplayName 显示名称 object_display_name
	 */	
	public void setObjectDisplayName (String objectDisplayName) {
		this.objectDisplayName = objectDisplayName;
	}
	
	/**
	 * 
	 * @return 正则表达式 regex
	 */
	public String getRegex(){
		return regex;
	}
	
	/**
	 * 
	 * @param regex 正则表达式 regex
	 */	
	public void setRegex (String regex) {
		this.regex = regex;
	}
	
	/**
	 * 
	 * @return 最大长度 char_max_length
	 */
	public Integer getCharMaxLength(){
		return charMaxLength;
	}
	
	/**
	 * 
	 * @param charMaxLength 最大长度 char_max_length
	 */	
	public void setCharMaxLength (Integer charMaxLength) {
		this.charMaxLength = charMaxLength;
	}
	
	/**
	 * 
	 * @return 最小长度 char_min_length
	 */
	public Integer getCharMinLength(){
		return charMinLength;
	}
	
	/**
	 * 
	 * @param charMinLength 最小长度 char_min_length
	 */	
	public void setCharMinLength (Integer charMinLength) {
		this.charMinLength = charMinLength;
	}
	
	/**
	 * 
	 * @return 最大值 max_value
	 */
	public String getMaxValue(){
		return maxValue;
	}
	
	/**
	 * 
	 * @param maxValue 最大值 max_value
	 */	
	public void setMaxValue (String maxValue) {
		this.maxValue = maxValue;
	}
	
	/**
	 * 
	 * @return 最小值 min_value
	 */
	public String getMinValue(){
		return minValue;
	}
	
	/**
	 * 
	 * @param minValue 最小值 min_value
	 */	
	public void setMinValue (String minValue) {
		this.minValue = minValue;
	}
	
	/**
	 * 
	 * @return 时间精度 datetime_precision
	 */
	public Integer getDatetimePrecision(){
		return datetimePrecision;
	}
	
	/**
	 * 
	 * @param datetimePrecision 时间精度 datetime_precision
	 */	
	public void setDatetimePrecision (Integer datetimePrecision) {
		this.datetimePrecision = datetimePrecision;
	}
	
	/**
	 * 
	 * @return 数据精度 numeric_precision
	 */
	public Integer getNumericPrecision(){
		return numericPrecision;
	}
	
	/**
	 * 
	 * @param numericPrecision 数据精度 numeric_precision
	 */	
	public void setNumericPrecision (Integer numericPrecision) {
		this.numericPrecision = numericPrecision;
	}
	
	/**
	 * 
	 * @return 数据小数精度 numeric_scale
	 */
	public Integer getNumericScale(){
		return numericScale;
	}
	
	/**
	 * 
	 * @param numericScale 数据小数精度 numeric_scale
	 */	
	public void setNumericScale (Integer numericScale) {
		this.numericScale = numericScale;
	}
	
	/**
	 * 
	 * @return 是否枚举(yes/no) is_enum
	 */
	public Integer getIsEnum(){
		return isEnum;
	}
	
	/**
	 * 
	 * @param isEnum 是否枚举(yes/no) is_enum
	 */	
	public void setIsEnum (Integer isEnum) {
		this.isEnum = isEnum;
	}
	
	/**
	 * 
	 * @return 枚举值 enum_key
	 */
	public String getEnumKey(){
		return enumKey;
	}
	
	/**
	 * 
	 * @param enumKey 枚举值 enum_key
	 */	
	public void setEnumKey (String enumKey) {
		this.enumKey = enumKey;
	}
	
	/**
	 * 
	 * @return 枚举显示值 enum_value
	 */
	public String getEnumValue(){
		return enumValue;
	}
	
	/**
	 * 
	 * @param enumValue 枚举显示值 enum_value
	 */	
	public void setEnumValue (String enumValue) {
		this.enumValue = enumValue;
	}
	


}
