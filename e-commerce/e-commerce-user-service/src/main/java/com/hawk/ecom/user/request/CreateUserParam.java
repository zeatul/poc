package com.hawk.ecom.user.request;

import com.hawk.framework.dic.validation.annotation.ExternalImplicitParam;
import com.hawk.framework.dic.validation.annotation.ExternalInputParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;

public class CreateUserParam {
	
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
	@ExternalInputParam(name="手机号")
	@NotEmpty
	private String mobileNumber;
	
	
	/**
	 * 密码,校验非空,长度,正则
	 */
	@ExternalInputParam(name="登录密码")
	@NotEmpty
	private String loginPwd;
	
	/**
	 * 为空的话,读取http请求的ip地址
	 */
	@ExternalImplicitParam
	private String registerIp;
	
	/**
	 * 读取http请求的userAgent
	 */
	@ExternalImplicitParam	
	private String userAgent;
	
	

}
