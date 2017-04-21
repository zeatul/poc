package com.hawk.ecom.user.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.framework.pub.web.ResponseData;

public class UserInfoResponse implements ResponseData{
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
	private String userCode;
	private String mobileNumber;
	@JsonIgnore
	private Long userId;
	
	
}
