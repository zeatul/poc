package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hawk.ecom.mall.request.MallCreateUserParam;
import com.hawk.ecom.pub.constant.ConstIdType;
import com.hawk.ecom.pub.constant.ConstSex;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MallUserControllerTest extends AbstractControllerTest{
	
//	@Test
	public void testMallLogin(){
		String url = getUrl("/mall/admin/user/login");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("loginPwd", "hawk@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testMallLogout(){
		String url = getUrl("/mall/admin/user/logout");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		params.add(new HttpParam("t","a6fd1449-c231-447d-bf4f-e87feee55de5"));
		String x = null;
		String result = httpExecutor.post(url, x, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testResetPassword(){
		String url = getUrl("/mall/admin/user/pwd/reset");  
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("veriCode", "123456");
		request.put("newPassword", "hawk1@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdatePassword(){
		String url = getUrl("/mall/admin/user/pwd/update");  
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		Map<String,String> request = new HashMap<String,String>();
		request.put("mobileNumber", "10000000000");
		request.put("oldPassword", "hawk1@1234");
		request.put("newPassword", "hawk@1234");
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testCreate(){
		String url = getUrl("/mall/admin/user/create"); 
		MallCreateUserParam request = new MallCreateUserParam();
		request.setIdType(ConstIdType.IDENTITY_CARD);
		request.setIdNumber("320109198809091234");
		request.setMobileNumber("13916082481");
		request.setUserName("隔壁老黄");
		request.setUserSex(ConstSex.MALE);
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		params.add(new HttpParam("t","e85c38fd-a218-4542-bc50-9d5da64b57f3"));		
		System.out.println("request="+JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
