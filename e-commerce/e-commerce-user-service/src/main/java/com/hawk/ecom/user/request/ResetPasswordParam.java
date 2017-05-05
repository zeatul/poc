package com.hawk.ecom.user.request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidWord;
import com.hawk.framework.pub.json.jackson.PasswordSerializer;
import com.hawk.framework.utility.tools.JsonTools;

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
	
//	@JsonIgnore
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@ValidWord
	@NotEmpty("mobileNumber")
	private String mobileNumber;
	
	@NotEmpty(name="验证码")
	private String veriCode;
	
	@ValidWord("loginPwd")
	@NotEmpty("loginPwd")
	@JsonSerialize(using=PasswordSerializer.class)
	private String newPassword;
	
	
	public static void main(String[] args){
		
		String jsonStr = "{\"mobileNumber\":\"1111\",\"newPassword\":\"1111\"}";
		ResetPasswordParam resetPasswordParam = JsonTools.toObject(jsonStr, ResetPasswordParam.class);
		
	
		
		System.out.println(JsonTools.toJsonString(resetPasswordParam));
	}
	
	

}
