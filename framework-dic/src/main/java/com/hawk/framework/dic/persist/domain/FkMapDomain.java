package com.hawk.framework.dic.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 外键字段匹配
 * The class is mapped to the table t_dic_fk_map 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class FkMapDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 object_id
	 */
	private String objectId;
	
	/**
	 * 外键对象ID fk_object_id
	 */
	private String fkObjectId;
	
	/**
	 * 主表字段对象ID parent_column_object_id
	 */
	private String parentColumnObjectId;
	
	/**
	 * 主表字段对象ID child_column_object_id
	 */
	private String childColumnObjectId;
	
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
	 * @return 主键 object_id
	 */
	public String getObjectId(){
		return objectId;
	}
	
	/**
	 * 
	 * @param objectId 主键 object_id
	 */	
	public void setObjectId (String objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 
	 * @return 外键对象ID fk_object_id
	 */
	public String getFkObjectId(){
		return fkObjectId;
	}
	
	/**
	 * 
	 * @param fkObjectId 外键对象ID fk_object_id
	 */	
	public void setFkObjectId (String fkObjectId) {
		this.fkObjectId = fkObjectId;
	}
	
	/**
	 * 
	 * @return 主表字段对象ID parent_column_object_id
	 */
	public String getParentColumnObjectId(){
		return parentColumnObjectId;
	}
	
	/**
	 * 
	 * @param parentColumnObjectId 主表字段对象ID parent_column_object_id
	 */	
	public void setParentColumnObjectId (String parentColumnObjectId) {
		this.parentColumnObjectId = parentColumnObjectId;
	}
	
	/**
	 * 
	 * @return 主表字段对象ID child_column_object_id
	 */
	public String getChildColumnObjectId(){
		return childColumnObjectId;
	}
	
	/**
	 * 
	 * @param childColumnObjectId 主表字段对象ID child_column_object_id
	 */	
	public void setChildColumnObjectId (String childColumnObjectId) {
		this.childColumnObjectId = childColumnObjectId;
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
