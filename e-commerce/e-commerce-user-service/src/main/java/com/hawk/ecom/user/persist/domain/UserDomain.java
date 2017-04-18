package com.hawk.ecom.user.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 用户注册表
 * The class is mapped to the table t_usr_user 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class UserDomain implements Serializable {

	private static final long serialVersionUID = -1L;
	
	/**
	 * 主键 id
	 */
	private Long id;
	
	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	/**
	 * 手机号 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 手机号是否已经验证 is_mobile_verified
	 */
	private Integer isMobileVerified;
	
	/**
	 * 手机号运营商 mobile_operator
	 */
	private String mobileOperator;
	
	/**
	 * 登录密码 login_pwd
	 */
	private String loginPwd;
	
	/**
	 * 用户状态 user_status
	 */
	private Integer userStatus;
	
	/**
	 * 状态变更日期 user_status_change_date
	 */
	private Date userStatusChangeDate;
	
	/**
	 * 注册渠道 register_channel
	 */
	private String registerChannel;
	
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
	 * 用户级别 user_level
	 */
	private Integer userLevel;
	
	/**
	 * 用户活跃度 user_activeness
	 */
	private Integer userActiveness;
	
	/**
	 * 注册IP register_ip
	 */
	private String registerIp;
	
	/**
	 * 证件类型 id_type
	 */
	private Integer idType;
	
	/**
	 * 证件号码 id_number
	 */
	private String idNumber;
	
	/**
	 * 设备唯一的串号 imei
	 */
	private String imei;
	
	/**
	 * 设备操作系统 operating_system
	 */
	private String operatingSystem;
	
	/**
	 * 设备操作系统版本号 operating_system_version
	 */
	private String operatingSystemVersion;
	
	/**
	 * 三星/华为/苹果 device_brand
	 */
	private String deviceBrand;
	
	/**
	 * 厂商给设备定义的编号 device_model
	 */
	private String deviceModel;
	
	/**
	 * http请求的user_agent原始信息 user_agent
	 */
	private String userAgent;
	
	/**
	 * 最近访问日期 last_access_date
	 */
	private Date lastAccessDate;
	
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
	 * 用户账号 user_account
	 */
	private String userAccount;
	
	/**
	 * 用户邮箱 user_email
	 */
	private String userEmail;
	
	/**
	 * 用户邮箱是否已验证 is_email_verified
	 */
	private Integer isEmailVerified;
	
	
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
	 * @return 手机号是否已经验证 is_mobile_verified
	 */
	public Integer getIsMobileVerified(){
		return isMobileVerified;
	}
	
	/**
	 * 
	 * @param isMobileVerified 手机号是否已经验证 is_mobile_verified
	 */	
	public void setIsMobileVerified (Integer isMobileVerified) {
		this.isMobileVerified = isMobileVerified;
	}
	
	/**
	 * 
	 * @return 手机号运营商 mobile_operator
	 */
	public String getMobileOperator(){
		return mobileOperator;
	}
	
	/**
	 * 
	 * @param mobileOperator 手机号运营商 mobile_operator
	 */	
	public void setMobileOperator (String mobileOperator) {
		this.mobileOperator = mobileOperator;
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
	 * @return 注册渠道 register_channel
	 */
	public String getRegisterChannel(){
		return registerChannel;
	}
	
	/**
	 * 
	 * @param registerChannel 注册渠道 register_channel
	 */	
	public void setRegisterChannel (String registerChannel) {
		this.registerChannel = registerChannel;
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
	 * @return 用户级别 user_level
	 */
	public Integer getUserLevel(){
		return userLevel;
	}
	
	/**
	 * 
	 * @param userLevel 用户级别 user_level
	 */	
	public void setUserLevel (Integer userLevel) {
		this.userLevel = userLevel;
	}
	
	/**
	 * 
	 * @return 用户活跃度 user_activeness
	 */
	public Integer getUserActiveness(){
		return userActiveness;
	}
	
	/**
	 * 
	 * @param userActiveness 用户活跃度 user_activeness
	 */	
	public void setUserActiveness (Integer userActiveness) {
		this.userActiveness = userActiveness;
	}
	
	/**
	 * 
	 * @return 注册IP register_ip
	 */
	public String getRegisterIp(){
		return registerIp;
	}
	
	/**
	 * 
	 * @param registerIp 注册IP register_ip
	 */	
	public void setRegisterIp (String registerIp) {
		this.registerIp = registerIp;
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
	 * @return 设备唯一的串号 imei
	 */
	public String getImei(){
		return imei;
	}
	
	/**
	 * 
	 * @param imei 设备唯一的串号 imei
	 */	
	public void setImei (String imei) {
		this.imei = imei;
	}
	
	/**
	 * 
	 * @return 设备操作系统 operating_system
	 */
	public String getOperatingSystem(){
		return operatingSystem;
	}
	
	/**
	 * 
	 * @param operatingSystem 设备操作系统 operating_system
	 */	
	public void setOperatingSystem (String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	
	/**
	 * 
	 * @return 设备操作系统版本号 operating_system_version
	 */
	public String getOperatingSystemVersion(){
		return operatingSystemVersion;
	}
	
	/**
	 * 
	 * @param operatingSystemVersion 设备操作系统版本号 operating_system_version
	 */	
	public void setOperatingSystemVersion (String operatingSystemVersion) {
		this.operatingSystemVersion = operatingSystemVersion;
	}
	
	/**
	 * 
	 * @return 三星/华为/苹果 device_brand
	 */
	public String getDeviceBrand(){
		return deviceBrand;
	}
	
	/**
	 * 
	 * @param deviceBrand 三星/华为/苹果 device_brand
	 */	
	public void setDeviceBrand (String deviceBrand) {
		this.deviceBrand = deviceBrand;
	}
	
	/**
	 * 
	 * @return 厂商给设备定义的编号 device_model
	 */
	public String getDeviceModel(){
		return deviceModel;
	}
	
	/**
	 * 
	 * @param deviceModel 厂商给设备定义的编号 device_model
	 */	
	public void setDeviceModel (String deviceModel) {
		this.deviceModel = deviceModel;
	}
	
	/**
	 * 
	 * @return http请求的user_agent原始信息 user_agent
	 */
	public String getUserAgent(){
		return userAgent;
	}
	
	/**
	 * 
	 * @param userAgent http请求的user_agent原始信息 user_agent
	 */	
	public void setUserAgent (String userAgent) {
		this.userAgent = userAgent;
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
	 * @return 用户邮箱是否已验证 is_email_verified
	 */
	public Integer getIsEmailVerified(){
		return isEmailVerified;
	}
	
	/**
	 * 
	 * @param isEmailVerified 用户邮箱是否已验证 is_email_verified
	 */	
	public void setIsEmailVerified (Integer isEmailVerified) {
		this.isEmailVerified = isEmailVerified;
	}
	


}
