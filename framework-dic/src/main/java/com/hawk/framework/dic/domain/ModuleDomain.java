package com.hawk.framework.dic.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 测试表的元数据使用,包括字段类型,索引,外键,不维护数据
 * The class is mapped to the table t_dic_module 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class ModuleDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 object_id
	 */
	private String objectId;
	
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
	 * 创建时间 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新时间 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除时间 delete_date
	 */
	private Date deleteDate;
	
	/**
	 * 长整型主键 id
	 */
	private String id;
	
	/**
	 * 编码 code
	 */
	private String code;
	
	/**
	 * 名称 name
	 */
	private String name;
	
	/**
	 * 描述 comment
	 */
	private String comment;
	
	/**
	 * uuid主键 uuid
	 */
	private String uuid;
	
	/**
	 * 密码 password
	 */
	private String password;
	
	/**
	 * 版本号 version
	 */
	private Integer version;
	
	
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
	 * @return 创建时间 create_date
	 */
	public Date getCreateDate(){
		return createDate;
	}
	
	/**
	 * 
	 * @param createDate 创建时间 create_date
	 */	
	public void setCreateDate (Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 
	 * @return 更新时间 update_date
	 */
	public Date getUpdateDate(){
		return updateDate;
	}
	
	/**
	 * 
	 * @param updateDate 更新时间 update_date
	 */	
	public void setUpdateDate (Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * 
	 * @return 删除时间 delete_date
	 */
	public Date getDeleteDate(){
		return deleteDate;
	}
	
	/**
	 * 
	 * @param deleteDate 删除时间 delete_date
	 */	
	public void setDeleteDate (Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	
	/**
	 * 
	 * @return 长整型主键 id
	 */
	public String getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 长整型主键 id
	 */	
	public void setId (String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 编码 code
	 */
	public String getCode(){
		return code;
	}
	
	/**
	 * 
	 * @param code 编码 code
	 */	
	public void setCode (String code) {
		this.code = code;
	}
	
	/**
	 * 
	 * @return 名称 name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param name 名称 name
	 */	
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return 描述 comment
	 */
	public String getComment(){
		return comment;
	}
	
	/**
	 * 
	 * @param comment 描述 comment
	 */	
	public void setComment (String comment) {
		this.comment = comment;
	}
	
	/**
	 * 
	 * @return uuid主键 uuid
	 */
	public String getUuid(){
		return uuid;
	}
	
	/**
	 * 
	 * @param uuid uuid主键 uuid
	 */	
	public void setUuid (String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 
	 * @return 密码 password
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * 
	 * @param password 密码 password
	 */	
	public void setPassword (String password) {
		this.password = password;
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
	


}
