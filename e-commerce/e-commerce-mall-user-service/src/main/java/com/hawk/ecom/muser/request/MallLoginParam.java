package com.hawk.ecom.muser.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.pub.json.jackson.PasswordSerializer;

public class MallLoginParam {
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

	public String getVeriCode() {
		return veriCode;
	}

	public void setVeriCode(String veriCode) {
		this.veriCode = veriCode;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getLoginChannel() {
		return loginChannel;
	}

	public void setLoginChannel(String loginChannel) {
		this.loginChannel = loginChannel;
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
	@JsonSerialize(using=PasswordSerializer.class)
	private String loginPwd;
	
	/**
	 * 验证码 
	 * 出错超过一定次数,登录需要提供验证码
	 */
	private String veriCode;
	
	
	/**
	 * 为空的话,读取http请求的ip地址
	 */
	private String loginIp;
	
	/**
	 * 读取http请求的userAgent
	 */
	private String userAgent;
	
	/**
	 * 登录渠道
	 */
	private String loginChannel;
}
