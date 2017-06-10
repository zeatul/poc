package com.hawk.ecom.mall.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 商城角色权限表
 * The class is mapped to the table t_mal_mall_role_right 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MallRoleRightDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 角色ID role_id
	 */
	private Long roleId;
	
	/**
	 * 权限ID right_id
	 */
	private Long rightId;
	
	/**
	 * 访问行为 right_op
	 */
	private Integer rightOp;
	
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
	 * @return 角色ID role_id
	 */
	public Long getRoleId(){
		return roleId;
	}
	
	/**
	 * 
	 * @param roleId 角色ID role_id
	 */	
	public void setRoleId (Long roleId) {
		this.roleId = roleId;
	}
	
	/**
	 * 
	 * @return 权限ID right_id
	 */
	public Long getRightId(){
		return rightId;
	}
	
	/**
	 * 
	 * @param rightId 权限ID right_id
	 */	
	public void setRightId (Long rightId) {
		this.rightId = rightId;
	}
	
	/**
	 * 
	 * @return 访问行为 right_op
	 */
	public Integer getRightOp(){
		return rightOp;
	}
	
	/**
	 * 
	 * @param rightOp 访问行为 right_op
	 */	
	public void setRightOp (Integer rightOp) {
		this.rightOp = rightOp;
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
