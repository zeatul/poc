package com.hawk.ecom.user.request;

import com.hawk.framework.dic.validation.annotation.ExternalInputParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class RegisterUserParam {
	

	
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
	private String loginPwd;

	
	@NotEmpty(value="veriCode",name="验证码")
	private String veriCode;

}
