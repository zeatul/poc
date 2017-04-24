package com.hawk.ecom.user.controller;

import org.junit.Test;

import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.framework.utility.tools.JsonTools;

public class UserControllerTest extends AbstractControllerTest{
	
//	@Test
	public void testHome(){
		String url = getUrl("/user/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}
	
	
	
	@Test
	public void testRegister(){
		String mobileNumber = "13916082481";
		String veriCode = "8640";
		RegisterUserParam registerUserParam = new RegisterUserParam ();
		registerUserParam.setMobileNumber(mobileNumber);
		registerUserParam.setVeriCode(veriCode);
		registerUserParam.setLoginPwd("123456");
		
		
		
		String url = getUrl("/user/register");
		
		System.out.println(JsonTools.toJsonString(registerUserParam));
		 
		String result = httpExecutor.post(url, registerUserParam, null);
			System.out.println("result=" + result);
	}

}
