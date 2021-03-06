package com.hawk.ecom.user.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.pub.json.jackson.PasswordSerializer;

public class RegisterUserParam {
	

	
	public String getRegisterChanel() {
		return registerChanel;
	}
	public void setRegisterChanel(String registerChanel) {
		this.registerChanel = registerChanel;
	}
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
	@ValidWord
	@NotEmpty
	private String mobileNumber;
	
	
	/**
	 * 密码,校验非空,长度,正则
	 */
	@NotEmpty
	@ValidWord
	@JsonSerialize(using=PasswordSerializer.class)
	private String loginPwd;

	
	@NotEmpty(name="验证码")
	private String veriCode;


	/**
	 * 注册渠道
	 */
	private String registerChanel;
	

}
