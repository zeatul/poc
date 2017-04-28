package com.hawk.framework.dic.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 数据字典定义
 * The class is mapped to the table t_dic_word 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class WordDomain implements Serializable {

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
	 * 名称 object_name
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
	 * 格式 object_pattern
	 */
	private String objectPattern;
	
	/**
	 * 格式描述 object_pattern_comment
	 */
	private String objectPatternComment;
	
	/**
	 * 正则表达式 regex
	 */
	private String regex;
	
	/**
	 * 扩展校验 object_extra_validation
	 */
	private String objectExtraValidation;
	
	/**
	 * 最大长度 char_max_length
	 */
	private Integer charMaxLength;
	
	/**
	 * 最小长度 char_min_length
	 */
	private Integer charMinLength;
	
	/**
	 * 是否有超过1个byte长度的的字符 is_only_ascii
	 */
	private Integer isOnlyAscii;
	
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
	 * 枚举值 enum_values
	 */
	private String enumValues;
	
	/**
	 * 系统编码(区分不同项目，不同集团) system_code
	 */
	private String systemCode;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
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
	 * @return 名称 object_name
	 */
	public String getObjectName(){
		return objectName;
	}
	
	/**
	 * 
	 * @param objectName 名称 object_name
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
	 * @return 格式 object_pattern
	 */
	public String getObjectPattern(){
		return objectPattern;
	}
	
	/**
	 * 
	 * @param objectPattern 格式 object_pattern
	 */	
	public void setObjectPattern (String objectPattern) {
		this.objectPattern = objectPattern;
	}
	
	/**
	 * 
	 * @return 格式描述 object_pattern_comment
	 */
	public String getObjectPatternComment(){
		return objectPatternComment;
	}
	
	/**
	 * 
	 * @param objectPatternComment 格式描述 object_pattern_comment
	 */	
	public void setObjectPatternComment (String objectPatternComment) {
		this.objectPatternComment = objectPatternComment;
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
	 * @return 扩展校验 object_extra_validation
	 */
	public String getObjectExtraValidation(){
		return objectExtraValidation;
	}
	
	/**
	 * 
	 * @param objectExtraValidation 扩展校验 object_extra_validation
	 */	
	public void setObjectExtraValidation (String objectExtraValidation) {
		this.objectExtraValidation = objectExtraValidation;
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
	 * @return 是否有超过1个byte长度的的字符 is_only_ascii
	 */
	public Integer getIsOnlyAscii(){
		return isOnlyAscii;
	}
	
	/**
	 * 
	 * @param isOnlyAscii 是否有超过1个byte长度的的字符 is_only_ascii
	 */	
	public void setIsOnlyAscii (Integer isOnlyAscii) {
		this.isOnlyAscii = isOnlyAscii;
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
	 * @return 枚举值 enum_values
	 */
	public String getEnumValues(){
		return enumValues;
	}
	
	/**
	 * 
	 * @param enumValues 枚举值 enum_values
	 */	
	public void setEnumValues (String enumValues) {
		this.enumValues = enumValues;
	}
	
	/**
	 * 
	 * @return 系统编码(区分不同项目，不同集团) system_code
	 */
	public String getSystemCode(){
		return systemCode;
	}
	
	/**
	 * 
	 * @param systemCode 系统编码(区分不同项目，不同集团) system_code
	 */	
	public void setSystemCode (String systemCode) {
		this.systemCode = systemCode;
	}
	
	/**
	 * 
	 * @return 版本号 version
	 */
	public Integer getVersion(){
		return version;
	}
	
	/**
	 * 
	 * @param version 版本号 version
	 */	
	public void setVersion (Integer version) {
		this.version = version;
	}
	
	/**
	 * 
	 * @return 创建日期 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建日期 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新日期 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新日期 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除日期 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除日期 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	


}
