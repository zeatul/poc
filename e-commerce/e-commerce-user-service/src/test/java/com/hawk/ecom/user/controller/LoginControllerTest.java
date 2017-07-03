package com.hawk.ecom.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.user.request.LoginParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class LoginControllerTest extends AbstractControllerTest{

	@Test
	public void testLogin(){
		String mobileNumber = "13800000000";
		String loginPwd = "123456";
		LoginParam loginParam = new LoginParam();
		loginParam.setMobileNumber(mobileNumber);
		loginParam.setLoginPwd(loginPwd);
		
		String url = getUrl("/user/login");
		System.out.println(JsonTools.toJsonString(loginParam));
		
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));	
		String result = httpExecutor.post(url, loginParam, params);
		System.out.println("result=" + result);
	}
}
