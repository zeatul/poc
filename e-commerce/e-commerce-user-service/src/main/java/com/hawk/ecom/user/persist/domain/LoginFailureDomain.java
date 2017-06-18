package com.hawk.ecom.user.persist.domain;
import java.io.Serializable;
import java.util.Date;




/**
 * 用户登录失败记录表
 * The class is mapped to the table t_usr_login_failure 
 * Don't modify this file as it will be regenerated frequently.
 * @author Code-Gen 
 */
public class LoginFailureDomain implements Serializable {

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
	 * 手机号 mobile_number
	 */
	private String mobileNumber;
	
	/**
	 * 登录IP login_ip
	 */
	private String loginIp;
	
	/**
	 * 登录类型(长期固定/短期固定/短期弹性) login_type
	 */
	private Integer loginType;
	
	/**
	 * 登录日期 login_date
	 */
	private Date loginDate;
	
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
	 * @return 主键 id
	 */
	public Integer getId(){
		return id;
	}
	
	/**
	 * 
	 * @param id 主键 id
	 */	
	public void setId (Integer  id) {
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
	 * @return 登录IP login_ip
	 */
	public String getLoginIp(){
		return loginIp;
	}
	
	/**
	 * 
	 * @param loginIp 登录IP login_ip
	 */	
	public void setLoginIp (String loginIp) {
		this.loginIp = loginIp;
	}
	
	/**
	 * 
	 * @return 登录类型(长期固定/短期固定/短期弹性) login_type
	 */
	public Integer getLoginType(){
		return loginType;
	}
	
	/**
	 * 
	 * @param loginType 登录类型(长期固定/短期固定/短期弹性) login_type
	 */	
	public void setLoginType (Integer loginType) {
		this.loginType = loginType;
	}
	
	/**
	 * 
	 * @return 登录日期 login_date
	 */
	public Date getLoginDate(){
		return loginDate;
	}
	
	/**
	 * 
	 * @param loginDate 登录日期 login_date
	 */	
	public void setLoginDate (Date loginDate) {
		this.loginDate = loginDate;
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
