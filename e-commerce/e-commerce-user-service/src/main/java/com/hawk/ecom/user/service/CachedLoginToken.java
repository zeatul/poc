package com.hawk.ecom.user.service;

public class CachedLoginToken {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(long expireDate) {
		this.expireDate = expireDate;
	}

	private String userCode;
	private String mobileNumber;
	private long userId;
	private long expireDate;
}
