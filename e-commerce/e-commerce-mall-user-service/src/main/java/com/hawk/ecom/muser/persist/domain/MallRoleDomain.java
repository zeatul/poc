package com.hawk.ecom.muser.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 商城角色表
 * The class is mapped to the table t_msr_mall_role 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MallRoleDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 角色编号 role_code
	 */
	private String roleCode;
	
	/**
	 * 角色名称 role_name
	 */
	private String roleName;
	
	/**
	 * 角色类型 role_type
	 */
	private Integer roleType;
	
	/**
	 * 系统保留 is_reserved
	 */
	private Integer isReserved;
	
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
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return 角色编号 role_code
	 */
	public String getRoleCode(){
		return roleCode;
	}
	
	/**
	 * 
	 * @param roleCode 角色编号 role_code
	 */	
	public void setRoleCode (String roleCode) {
		this.roleCode = roleCode;
	}
	
	/**
	 * 
	 * @return 角色名称 role_name
	 */
	public String getRoleName(){
		return roleName;
	}
	
	/**
	 * 
	 * @param roleName 角色名称 role_name
	 */	
	public void setRoleName (String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * 
	 * @return 角色类型 role_type
	 */
	public Integer getRoleType(){
		return roleType;
	}
	
	/**
	 * 
	 * @param roleType 角色类型 role_type
	 */	
	public void setRoleType (Integer roleType) {
		this.roleType = roleType;
	}
	
	/**
	 * 
	 * @return 系统保留 is_reserved
	 */
	public Integer getIsReserved(){
		return isReserved;
	}
	
	/**
	 * 
	 * @param isReserved 系统保留 is_reserved
	 */	
	public void setIsReserved (Integer isReserved) {
		this.isReserved = isReserved;
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
