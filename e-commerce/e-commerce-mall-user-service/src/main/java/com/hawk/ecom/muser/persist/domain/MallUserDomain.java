package com.hawk.ecom.muser.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 商城用户表
 * The class is mapped to the table t_msr_mall_user 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class MallUserDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Integer id;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 用户账号 user_account
	 */
	private String userAccount;
	
	/**
	 * 用户邮箱 user_email
	 */
	private String userEmail;
	
	/**
	 * 用户类型 user_type
	 */
	private Integer userType;
	
	/**
	 * 手机号 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 登录密码 login_pwd
	 */
	private String loginPwd;
	
	/**
	 * 密码已经修改次数 pwd_update_times
	 */
	private Integer pwdUpdateTimes;
	
	/**
	 * 密码最近修改时间 last_pwd_update_date
	 */
	private Date lastPwdUpdateDate;
	
	/**
	 * 用户昵称 user_nickname
	 */
	private String userNickname;
	
	/**
	 * 用户姓名 user_name
	 */
	private String userName;
	
	/**
	 * 用户性别 user_sex
	 */
	private String userSex;
	
	/**
	 * 用户生日 user_birthday
	 */
	private Date userBirthday;
	
	/**
	 * 用户头像 user_picture
	 */
	private String userPicture;
	
	/**
	 * 证件类型 id_type
	 */
	private Integer idType;
	
	/**
	 * 证件号码 id_number
	 */
	private String idNumber;
	
	/**
	 * 用户状态 user_status
	 */
	private Integer userStatus;
	
	/**
	 * 状态变更原因 user_status_change_cause
	 */
	private String userStatusChangeCause;
	
	/**
	 * 状态变更日期 user_status_change_date
	 */
	private Date userStatusChangeDate;
	
	/**
	 * 最近访问日期 last_access_date
	 */
	private Date lastAccessDate;
	
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
	 * @return 用户编号 user_code
	 */
	public String getUserCode(){
		return userCode;
	}
	
	/**
	 * 
	 * @param userCode 用户编号 user_code
	 */	
	public void setUserCode (String userCode) {
		this.userCode = userCode;
	}
	
	/**
	 * 
	 * @return 用户账号 user_account
	 */
	public String getUserAccount(){
		return userAccount;
	}
	
	/**
	 * 
	 * @param userAccount 用户账号 user_account
	 */	
	public void setUserAccount (String userAccount) {
		this.userAccount = userAccount;
	}
	
	/**
	 * 
	 * @return 用户邮箱 user_email
	 */
	public String getUserEmail(){
		return userEmail;
	}
	
	/**
	 * 
	 * @param userEmail 用户邮箱 user_email
	 */	
	public void setUserEmail (String userEmail) {
		this.userEmail = userEmail;
	}
	
	/**
	 * 
	 * @return 用户类型 user_type
	 */
	public Integer getUserType(){
		return userType;
	}
	
	/**
	 * 
	 * @param userType 用户类型 user_type
	 */	
	public void setUserType (Integer userType) {
		this.userType = userType;
	}
	
	/**
	 * 
	 * @return 手机号 mobile_number
	 */
	public String getMobileNumber(){
		return mobileNumber;
	}
	
	/**
	 * 
	 * @param mobileNumber 手机号 mobile_number
	 */	
	public void setMobileNumber (String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * 
	 * @return 登录密码 login_pwd
	 */
	public String getLoginPwd(){
		return loginPwd;
	}
	
	/**
	 * 
	 * @param loginPwd 登录密码 login_pwd
	 */	
	public void setLoginPwd (String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	/**
	 * 
	 * @return 密码已经修改次数 pwd_update_times
	 */
	public Integer getPwdUpdateTimes(){
		return pwdUpdateTimes;
	}
	
	/**
	 * 
	 * @param pwdUpdateTimes 密码已经修改次数 pwd_update_times
	 */	
	public void setPwdUpdateTimes (Integer pwdUpdateTimes) {
		this.pwdUpdateTimes = pwdUpdateTimes;
	}
	
	/**
	 * 
	 * @return 密码最近修改时间 last_pwd_update_date
	 */
	public Date getLastPwdUpdateDate(){
		return lastPwdUpdateDate;
	}
	
	/**
	 * 
	 * @param lastPwdUpdateDate 密码最近修改时间 last_pwd_update_date
	 */	
	public void setLastPwdUpdateDate (Date lastPwdUpdateDate) {
		this.lastPwdUpdateDate = lastPwdUpdateDate;
	}
	
	/**
	 * 
	 * @return 用户昵称 user_nickname
	 */
	public String getUserNickname(){
		return userNickname;
	}
	
	/**
	 * 
	 * @param userNickname 用户昵称 user_nickname
	 */	
	public void setUserNickname (String userNickname) {
		this.userNickname = userNickname;
	}
	
	/**
	 * 
	 * @return 用户姓名 user_name
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * 
	 * @param userName 用户姓名 user_name
	 */	
	public void setUserName (String userName) {
		this.userName = userName;
	}
	
	/**
	 * 
	 * @return 用户性别 user_sex
	 */
	public String getUserSex(){
		return userSex;
	}
	
	/**
	 * 
	 * @param userSex 用户性别 user_sex
	 */	
	public void setUserSex (String userSex) {
		this.userSex = userSex;
	}
	
	/**
	 * 
	 * @return 用户生日 user_birthday
	 */
	public Date getUserBirthday(){
		return userBirthday;
	}
	
	/**
	 * 
	 * @param userBirthday 用户生日 user_birthday
	 */	
	public void setUserBirthday (Date userBirthday) {
		this.userBirthday = userBirthday;
	}
	
	/**
	 * 
	 * @return 用户头像 user_picture
	 */
	public String getUserPicture(){
		return userPicture;
	}
	
	/**
	 * 
	 * @param userPicture 用户头像 user_picture
	 */	
	public void setUserPicture (String userPicture) {
		this.userPicture = userPicture;
	}
	
	/**
	 * 
	 * @return 证件类型 id_type
	 */
	public Integer getIdType(){
		return idType;
	}
	
	/**
	 * 
	 * @param idType 证件类型 id_type
	 */	
	public void setIdType (Integer idType) {
		this.idType = idType;
	}
	
	/**
	 * 
	 * @return 证件号码 id_number
	 */
	public String getIdNumber(){
		return idNumber;
	}
	
	/**
	 * 
	 * @param idNumber 证件号码 id_number
	 */	
	public void setIdNumber (String idNumber) {
		this.idNumber = idNumber;
	}
	
	/**
	 * 
	 * @return 用户状态 user_status
	 */
	public Integer getUserStatus(){
		return userStatus;
	}
	
	/**
	 * 
	 * @param userStatus 用户状态 user_status
	 */	
	public void setUserStatus (Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	/**
	 * 
	 * @return 状态变更原因 user_status_change_cause
	 */
	public String getUserStatusChangeCause(){
		return userStatusChangeCause;
	}
	
	/**
	 * 
	 * @param userStatusChangeCause 状态变更原因 user_status_change_cause
	 */	
	public void setUserStatusChangeCause (String userStatusChangeCause) {
		this.userStatusChangeCause = userStatusChangeCause;
	}
	
	/**
	 * 
	 * @return 状态变更日期 user_status_change_date
	 */
	public Date getUserStatusChangeDate(){
		return userStatusChangeDate;
	}
	
	/**
	 * 
	 * @param userStatusChangeDate 状态变更日期 user_status_change_date
	 */	
	public void setUserStatusChangeDate (Date userStatusChangeDate) {
		this.userStatusChangeDate = userStatusChangeDate;
	}
	
	/**
	 * 
	 * @return 最近访问日期 last_access_date
	 */
	public Date getLastAccessDate(){
		return lastAccessDate;
	}
	
	/**
	 * 
	 * @param lastAccessDate 最近访问日期 last_access_date
	 */	
	public void setLastAccessDate (Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
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
