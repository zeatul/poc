package com.hawk.framework.dic.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 同义词定义表
 * The class is mapped to the table t_dic_synonym 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class SynonymDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 原单词编码 origin_object_code
	 */
	private String originObjectCode;
	
	/**
	 * 同义词编码 synonym_object_code
	 */
	private String synonymObjectCode;
	
	/**
	 * 同义词显示名称 synonym_display_name
	 */
	private String synonymDisplayName;
	
	/**
	 * 同义词类型 synonym_type
	 */
	private String synonymType;
	
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
	 * @return 原单词编码 origin_object_code
	 */
	public String getOriginObjectCode(){
		return originObjectCode;
	}
	
	/**
	 * 
	 * @param originObjectCode 原单词编码 origin_object_code
	 */	
	public void setOriginObjectCode (String originObjectCode) {
		this.originObjectCode = originObjectCode;
	}
	
	/**
	 * 
	 * @return 同义词编码 synonym_object_code
	 */
	public String getSynonymObjectCode(){
		return synonymObjectCode;
	}
	
	/**
	 * 
	 * @param synonymObjectCode 同义词编码 synonym_object_code
	 */	
	public void setSynonymObjectCode (String synonymObjectCode) {
		this.synonymObjectCode = synonymObjectCode;
	}
	
	/**
	 * 
	 * @return 同义词显示名称 synonym_display_name
	 */
	public String getSynonymDisplayName(){
		return synonymDisplayName;
	}
	
	/**
	 * 
	 * @param synonymDisplayName 同义词显示名称 synonym_display_name
	 */	
	public void setSynonymDisplayName (String synonymDisplayName) {
		this.synonymDisplayName = synonymDisplayName;
	}
	
	/**
	 * 
	 * @return 同义词类型 synonym_type
	 */
	public String getSynonymType(){
		return synonymType;
	}
	
	/**
	 * 
	 * @param synonymType 同义词类型 synonym_type
	 */	
	public void setSynonymType (String synonymType) {
		this.synonymType = synonymType;
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
