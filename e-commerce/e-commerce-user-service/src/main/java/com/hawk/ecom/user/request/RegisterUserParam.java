package com.hawk.ecom.user.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hawk.framework.dic.validation.annotation.ExternalInputParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.pub.json.jackson.PasswordSerializer;

public class RegisterUserParam {
	

	
	public String getVeriCode() {
		return veriCode;
	}
	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	
	/**
	 * 手机号,校验非空,长度,正则
	 */
	@ExternalInputParam(name="手机号")
	@NotEmpty("mobileNumber")
	private String mobileNumber;
	
	
	/**
	 * 密码,校验非空,长度,正则
	 */
	@ExternalInputParam(name="登录密码")
	@NotEmpty("loginPwd")
	@JsonSerialize(using=PasswordSerializer.class)
	private String loginPwd;

	
	@NotEmpty(value="veriCode",name="验证码")
	private String veriCode;


	

}
