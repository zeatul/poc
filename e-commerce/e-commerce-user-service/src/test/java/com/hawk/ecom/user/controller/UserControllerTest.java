package com.hawk.ecom.user.controller;

import org.junit.Test;

import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.ResetPasswordParam;
import com.hawk.ecom.user.request.UpdatePasswordParam;
import com.hawk.framework.utility.tools.JsonTools;

public class UserControllerTest extends AbstractControllerTest {

	// @Test
	public void testHome() {
		String url = getUrl("/user/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}

//	 @Test
	public void testRegister() {
		String mobileNumber = "13916082481";
		String veriCode = "8640";
		RegisterUserParam registerUserParam = new RegisterUserParam();
		registerUserParam.setMobileNumber(mobileNumber);
		registerUserParam.setVeriCode(veriCode);
		registerUserParam.setLoginPwd("123456");

		String url = getUrl("/user/register");

		System.out.println(JsonTools.toJsonString(registerUserParam));

		String result = httpExecutor.post(url, registerUserParam, null);
		System.out.println("result=" + result);
	}

//	@Test
	public void testUpdatePassword() {
		UpdatePasswordParam updatePasswordParam = new UpdatePasswordParam();
		String mobileNumber = "13411082481";
		String oldPassword = "123456";
		String newPassword = "654321";
		updatePasswordParam.setMobileNumber(mobileNumber);
		updatePasswordParam.setOldPassword(oldPassword);
		updatePasswordParam.setNewPassword(newPassword);

		String url = getUrl("/user/pwd/update");
		System.out.println(JsonTools.toJsonString(updatePasswordParam));

		String result = httpExecutor.post(url, updatePasswordParam, null);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testResetPassword(){
		ResetPasswordParam param = new ResetPasswordParam();
		String mobileNumber = "13411082481";
		String veriCode = "123456";
		String newPassword = "654321";
		param.setMobileNumber(mobileNumber);
		param.setNewPassword(newPassword);
		param.setVeriCode(veriCode);
		
		String url = getUrl("/user/pwd/reset");
		System.out.println(JsonTools.toJsonString(param));

		String result = httpExecutor.post(url, param, null);
		System.out.println("result=" + result);
	}

}
