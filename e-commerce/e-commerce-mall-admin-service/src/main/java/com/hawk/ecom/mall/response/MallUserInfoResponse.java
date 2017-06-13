package com.hawk.ecom.mall.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.framework.pub.web.ResponseData;

public class MallUserInfoResponse implements ResponseData{
	
	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserSex() {
		return userSex;
	}


	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}


	public Integer getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getUserBirthday() {
		return userBirthday;
	}


	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}


	/**
	 * 用户编号 user_code
	 */
	private String userCode;
	
	
	/**
	 * 手机号 mobile_number
	 */
	private String mobileNumber;
	

	
	/**
	 * 用户姓名 user_name
	 */
	private String userName;
	
	/**
	 * 用户性别 user_sex
	 */
	private String userSex;
	
	
	/**
	 * 用户状态 user_status
	 */
	private Integer userStatus;
	

	
	/**
	 * 创建日期 create_date
	 */
	private Date createDate;
	
	
	/**
	 * 用户生日 user_birthday
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date userBirthday;
	

	
	
	
	
	
	
	

}
