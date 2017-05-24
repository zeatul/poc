package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MallUserControllerTest extends AbstractControllerTest{
	
//	@Test
	public void testMallLogin(){
		String url = getUrl("/mall/admin/user/login");
		
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("loginPwd", "hawk@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testMallLogout(){
		String url = getUrl("/mall/admin/user/logout");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		params.add(new HttpParam("t","151d23bf-1350-4fde-82c1-1fd71ecdd679"));
		String x = null;
		String result = httpExecutor.post(url, x, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testResetPassword(){
		String url = getUrl("/mall/admin/user/pwd/reset");  
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("veriCode", "123456");
		request.put("newPassword", "hawk1@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdatePassword(){
		String url = getUrl("/mall/admin/user/pwd/update");  
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("oldPassword", "hawk1@1234");
		request.put("newPassword", "hawk@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, null);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testCreate(){
		
	}
}
