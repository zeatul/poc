package com.hawk.framework.dic.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 枚举
 * The class is mapped to the table t_dic_enum 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class EnumDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 对象ID object_id
	 */
	private String objectId;
	
	/**
	 * 单词主键 word_object_id
	 */
	private String wordObjectId;
	
	/**
	 * 枚举键 enum_key
	 */
	private String enumKey;
	
	/**
	 * 枚举值 enum_value
	 */
	private String enumValue;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
	/**
	 * 系统编码(区分不同项目，不同集团) system_code
	 */
	private String systemCode;
	
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
	 * @return 单词主键 word_object_id
	 */
	public String getWordObjectId(){
		return wordObjectId;
	}
	
	/**
	 * 
	 * @param wordObjectId 单词主键 word_object_id
	 */	
	public void setWordObjectId (String wordObjectId) {
		this.wordObjectId = wordObjectId;
	}
	
	/**
	 * 
	 * @return 枚举键 enum_key
	 */
	public String getEnumKey(){
		return enumKey;
	}
	
	/**
	 * 
	 * @param enumKey 枚举键 enum_key
	 */	
	public void setEnumKey (String enumKey) {
		this.enumKey = enumKey;
	}
	
	/**
	 * 
	 * @return 枚举值 enum_value
	 */
	public String getEnumValue(){
		return enumValue;
	}
	
	/**
	 * 
	 * @param enumValue 枚举值 enum_value
	 */	
	public void setEnumValue (String enumValue) {
		this.enumValue = enumValue;
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
