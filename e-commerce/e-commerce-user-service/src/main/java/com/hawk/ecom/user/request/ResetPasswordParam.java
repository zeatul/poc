package com.hawk.ecom.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class ResetPasswordParam {
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getVeriCode() {
		return veriCode;
	}
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@NotEmpty("mobileNumber")
	private String mobileNumber;
	@NotEmpty(value="veriCode",name="验证码")
	private String veriCode;
	@NotEmpty(value="loginPwd",name="新密码码")
	@JsonIgnore
	private String newPassword;

}
