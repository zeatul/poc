package com.hawk.ecom.product.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 图片表
 * The class is mapped to the table t_prd_pic 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class PicDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 所有者类型 owner_type
	 */
	private Integer ownerType;
	
	/**
	 * 所有者主键 ownert_id
	 */
	private Long ownertId;
	
	/**
	 * 图片名称 pic_name
	 */
	private String picName;
	
	/**
	 * 图片地址 pic_url
	 */
	private String picUrl;
	
	/**
	 * 图片类型 pic_type
	 */
	private Integer picType;
	
	/**
	 * 创建者 create_user_code
	 */
	private String createUserCode;
	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	/**
	 * 更新者 update_user_code
	 */
	private String updateUserCode;
	
	/**
	 * 更新日期 update_date
	 */
	private Date updateDate;
	
	/**
	 * 删除者 delete_user_code
	 */
	private String deleteUserCode;
	
	/**
	 * 删除日期 delete_date
	 */
	private Date deleteDate;
	
	
	/**
	 * 
	 * @return 主键 id
	 */
	public Long getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 所有者类型 owner_type
	 */
	public Integer getOwnerType(){
		return ownerType;
	}
	
	/**
	 * 
	 * @param ownerType 所有者类型 owner_type
	 */	
	public void setOwnerType (Integer ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 
	 * @return 所有者主键 ownert_id
	 */
	public Long getOwnertId(){
		return ownertId;
	}
	
	/**
	 * 
	 * @param ownertId 所有者主键 ownert_id
	 */	
	public void setOwnertId (Long ownertId) {
		this.ownertId = ownertId;
	}
	
	/**
	 * 
	 * @return 图片名称 pic_name
	 */
	public String getPicName(){
		return picName;
	}
	
	/**
	 * 
	 * @param picName 图片名称 pic_name
	 */	
	public void setPicName (String picName) {
		this.picName = picName;
	}
	
	/**
	 * 
	 * @return 图片地址 pic_url
	 */
	public String getPicUrl(){
		return picUrl;
	}
	
	/**
	 * 
	 * @param picUrl 图片地址 pic_url
	 */	
	public void setPicUrl (String picUrl) {
		this.picUrl = picUrl;
	}
	
	/**
	 * 
	 * @return 图片类型 pic_type
	 */
	public Integer getPicType(){
		return picType;
	}
	
	/**
	 * 
	 * @param picType 图片类型 pic_type
	 */	
	public void setPicType (Integer picType) {
		this.picType = picType;
	}
	
	/**
	 * 
	 * @return 创建者 create_user_code
	 */
	public String getCreateUserCode(){
		return createUserCode;
	}
	
	/**
	 * 
	 * @param createUserCode 创建者 create_user_code
	 */	
	public void setCreateUserCode (String createUserCode) {
		this.createUserCode = createUserCode;
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
	 * @return 更新者 update_user_code
	 */
	public String getUpdateUserCode(){
		return updateUserCode;
	}
	
	/**
	 * 
	 * @param updateUserCode 更新者 update_user_code
	 */	
	public void setUpdateUserCode (String updateUserCode) {
		this.updateUserCode = updateUserCode;
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
	 * @return 删除者 delete_user_code
	 */
	public String getDeleteUserCode(){
		return deleteUserCode;
	}
	
	/**
	 * 
	 * @param deleteUserCode 删除者 delete_user_code
	 */	
	public void setDeleteUserCode (String deleteUserCode) {
		this.deleteUserCode = deleteUserCode;
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