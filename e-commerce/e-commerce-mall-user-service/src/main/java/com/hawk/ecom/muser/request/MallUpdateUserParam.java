package com.hawk.ecom.muser.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hawk.ecom.muser.annotation.MallNotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;

public class MallUpdateUserParam {


	public Date getUserBirthday() {
		return userBirthday;
	}



	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
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



	public String getUserCode() {
		return userCode;
	}



	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}



	public String getOperatorCode() {
		return operatorCode;
	}



	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}



	/**
	 * 用户姓名 user_name
	 */
	@ValidWord
	private String userName;
	
	/**
	 * 用户性别 user_sex
	 */
	@ValidWord
	private String userSex;
	
	
	

	/**
	 * 用户编号 user_code
	 */
	@ValidWord
	@NotEmpty
	private String userCode;
	
	
	
	
	
	/**
	 * 用户生日 user_birthday
	 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date userBirthday;
	
	/**
	 * 操作员编号
	 */
	@MallNotLogin
	private String operatorCode;
}
