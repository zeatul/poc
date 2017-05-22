package com.hawk.ecom.mall.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.framework.pub.web.ResponseData;

public class MallUserInfoResponse implements ResponseData{
	
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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
	 * 用户Id
	 */
	private Long userId;
	

	
	
	
	
	
	
	

}
