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
	
	/*对象ID object_id*/
	private String objectId;
	
	/*用途类型 use_type*/
	private String useType;
	
	/*数据类型 data_type*/
	private String dataType;
	
	/*标签 object_label*/
	private String objectLabel;
	
	/*描述 description*/
	private String description;
	
	/*中文名 cname*/
	private String cname;
	
	/*正则 regex*/
	private String regex;
	
	/*最大长度 max_length*/
	private Integer maxLength;
	
	/*最小长度 min_length*/
	private Integer minLength;
	
	/*最大值 max_value*/
	private String maxValue;
	
	/*最小值 min_value*/
	private String minValue;
	
	
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
	 * @return 标签 object_label
	 */
	public String getObjectLabel(){
		return objectLabel;
	}
	
	/**
	 * 
	 * @param objectLabel 标签 object_label
	 */	
	public void setObjectLabel (String objectLabel) {
		this.objectLabel = objectLabel;
	}
	
	/**
	 * 
	 * @return 描述 description
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * 
	 * @param description 描述 description
	 */	
	public void setDescription (String description) {
		this.description = description;
	}
	
	/**
	 * 
	 * @return 中文名 cname
	 */
	public String getCname(){
		return cname;
	}
	
	/**
	 * 
	 * @param cname 中文名 cname
	 */	
	public void setCname (String cname) {
		this.cname = cname;
	}
	
	/**
	 * 
	 * @return 正则 regex
	 */
	public String getRegex(){
		return regex;
	}
	
	/**
	 * 
	 * @param regex 正则 regex
	 */	
	public void setRegex (String regex) {
		this.regex = regex;
	}
	
	/**
	 * 
	 * @return 最大长度 max_length
	 */
	public Integer getMaxLength(){
		return maxLength;
	}
	
	/**
	 * 
	 * @param maxLength 最大长度 max_length
	 */	
	public void setMaxLength (Integer maxLength) {
		this.maxLength = maxLength;
	}
	
	/**
	 * 
	 * @return 最小长度 min_length
	 */
	public Integer getMinLength(){
		return minLength;
	}
	
	/**
	 * 
	 * @param minLength 最小长度 min_length
	 */	
	public void setMinLength (Integer minLength) {
		this.minLength = minLength;
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
	


}
