package com.hawk.ecom.user.controller;

import org.junit.Test;

import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.SendVeriCodeParam;
import com.hawk.framework.utility.tools.JsonTools;

public class UserControllerTest extends AbstractControllerTest{
	
	@Test
	public void testHome(){
		String url = getUrl("/user/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}
	
	@Test
	public void testVeriCode(){
		String mobileNumber = "13916082481";
		SendVeriCodeParam sendVeriCodeParam = new SendVeriCodeParam();
		sendVeriCodeParam.setMobileNumber(mobileNumber);
		String url = getUrl("/user/veriCode");
		System.out.println(JsonTools.toJsonString(sendVeriCodeParam));
		String result = httpExecutor.post(url, sendVeriCodeParam, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testRegister(){
		String mobileNumber = "13916082481";
		String veriCode = "0712";
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
