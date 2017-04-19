package com.hawk.ecom.user.controller;

import org.junit.Test;

import com.hawk.ecom.user.request.LoginParam;
import com.hawk.framework.utility.tools.JsonTools;

public class LoginControllerTest extends AbstractControllerTest{

	@Test
	public void testLogin(){
		String mobileNumber = "13916082481";
		String loginPwd = "123456";
		LoginParam loginParam = new LoginParam();
		loginParam.setMobileNumber(mobileNumber);
		loginParam.setLoginPwd(loginPwd);
		
		String url = getUrl("/user/login");
		System.out.println(JsonTools.toJsonString(loginParam));
		String result = httpExecutor.post(url, loginParam, null);
		System.out.println("result=" + result);
	}
}
