package com.hawk.ecom.muser.request;

import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class MallCreateUserParam {

	
	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRegisterChannel() {
		return registerChannel;
	}

	public void setRegisterChannel(String registerChannel) {
		this.registerChannel = registerChannel;
	}

	

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	/**
	 * 手机号 mobile_number
	 */
	@ValidWord
	@NotEmpty
	private String mobileNumber;

	/**
	 * 用户姓名 user_name
	 */
	@ValidWord
	@NotEmpty
	private String userName;
	
	/**
	 * 用户性别 user_sex
	 */
	@ValidWord
	private String userSex;
	
	/**
	 * 证件类型 id_type
	 */
	@ValidWord
	@NotEmpty
	private Integer idType = 1;
	
	/**
	 * 证件号码 id_number
	 */
	@ValidWord
	@NotEmpty
	private String idNumber;
	

	/**
	 * 为空的话,读取http请求的ip地址
	 */	
	private String registerIp;
	
	/**
	 * 读取http请求的userAgent
	 */
	
	private String userAgent;
	
	/**
	 * 注册渠道
	 */
	private String registerChannel;
	
	
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
