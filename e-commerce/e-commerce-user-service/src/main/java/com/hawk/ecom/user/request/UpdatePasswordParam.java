package com.hawk.ecom.user.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.pub.json.jackson.PasswordSerializer;

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

	@NotEmpty(value="loginPwd",name="新密码")
	@JsonSerialize(using=PasswordSerializer.class)
	private String oldPassword;
	
	@ValidWord(value="loginPwd",name="新密码")
	@NotEmpty(value="loginPwd",name="新密码")	
	@JsonSerialize(using=PasswordSerializer.class)
	private String newPassword;
}
