package com.hawk.ecom.user.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class CreateUserParam {
	
	public String getRegisterChannel() {
		return registerChannel;
	}
	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
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
	@ValidWord("mobileNumber")
	@NotEmpty("mobileNumber")
	private String mobileNumber;
	
	
	/**
	 * 密码,校验非空,长度,正则
	 */
	@ValidWord("loginPwd")
	@NotEmpty("loginPwd")
	@JsonIgnore
	private String loginPwd;
	
	/**
	 * 为空的话,读取http请求的ip地址
	 */	
	private String registerIp;
	
	/**
	 * 读取http请求的userAgent
	 */
	
	private String userAgent;
	
	
	private String registerChannel;
	
	

}
