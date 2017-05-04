package com.hawk.ecom.user.request;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class LoginParam {

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

	/**
	 * 手机号,校验非空,长度,正则
	 */
	@ValidWord
	@NotEmpty
	private String mobileNumber;
	
	
	/**
	 * 密码,校验非空,长度,正则
	 */
	@ValidWord
	@NotEmpty
	private String loginPwd;
	
	/**
	 * 为空的话,读取http请求的ip地址
	 */
	private String loginIp;
	
	/**
	 * 读取http请求的userAgent
	 */
	private String userAgent;
}
