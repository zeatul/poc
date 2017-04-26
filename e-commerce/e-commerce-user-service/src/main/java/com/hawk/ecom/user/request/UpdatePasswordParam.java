package com.hawk.ecom.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class UpdatePasswordParam {
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@NotEmpty(value="mobileNumber")
	private String mobileNumber;

	@NotEmpty(value="loginPwd",name="旧密码")
	private String oldPassword;
	
	@NotEmpty(value="loginPwd",name="新密码")	
	private String newPassword;
}
