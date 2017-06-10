package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.hawk.ecom.mall.constant.ConstMallUserStatus;
import com.hawk.ecom.mall.request.MallCreateUserParam;
import com.hawk.ecom.mall.request.MallListUserParam;
import com.hawk.ecom.mall.request.MallUpdateUserParam;
import com.hawk.ecom.mall.request.MallUpdateUserStatusParam;
import com.hawk.ecom.pub.constant.ConstIdType;
import com.hawk.ecom.pub.constant.ConstSex;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MallUserControllerTest extends AbstractControllerTest {
	
	private String token = "e8c23558-5ed2-42cd-873a-6bf63dfee155";

//	@Test
	public void testMallLogin() {
		String url = getUrl("/mall/admin/user/login");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		Map<String, String> request = new HashMap<String, String>();
		request.put("mobileNumber", "10000000000"); // superadmin
//		request.put("mobileNumber", "10000000001"); // admin
		request.put("loginPwd", "hawk@1234");
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	
	@Test
	public void testMallLoginInfo(){
		String url = getUrl("/mall/admin/user/login/info");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testMallLogout() {
		String url = getUrl("/mall/admin/user/logout");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "a6fd1449-c231-447d-bf4f-e87feee55de5"));
		String x = null;
		String result = httpExecutor.post(url, x, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testResetPassword() {
		String url = getUrl("/mall/admin/user/pwd/reset");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		Map<String, String> request = new HashMap<String, String>();
		request.put("mobileNumber", "10000000000");
		request.put("veriCode", "123456");
		request.put("newPassword", "hawk1@1234");
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testUpdatePassword() {
		String url = getUrl("/mall/admin/user/pwd/update");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		Map<String, String> request = new HashMap<String, String>();
		request.put("mobileNumber", "10000000000");
		request.put("oldPassword", "hawk1@1234");
		request.put("newPassword", "hawk@1234");
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testCreate() {
		String url = getUrl("/mall/admin/user/create");
		MallCreateUserParam request = new MallCreateUserParam();
		request.setIdType(ConstIdType.IDENTITY_CARD);
		request.setIdNumber("320103198809091236");
		request.setMobileNumber("13816082486");
		request.setUserName("隔壁老刘");
		request.setUserSex(ConstSex.MALE);
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testUpdate() {
		String url = getUrl("/mall/admin/user/update");
		MallUpdateUserParam request = new MallUpdateUserParam();
		request.setUserCode("1111");
		request.setUserName("hlllo");
		request.setUserSex(ConstSex.MALE);
		request.setUserBirthday(new Date());

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testUpdateStatus() {
		String url = getUrl("/mall/admin/user/status/update");
		MallUpdateUserStatusParam request = new MallUpdateUserStatusParam();
		request.setUserStatus(ConstMallUserStatus.FORBIDDEN);
		request.setUserCodes(Arrays.asList("u1", "u2"));

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testList() {
		String url = getUrl("/mall/admin/user/list");
		MallListUserParam request = new MallListUserParam();
		request.setPageRowCount(100);
		request.setPageIndex(1);
		request.setOrder("create_date desc");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "2cf4c674-7fd4-496d-8354-d2f8f0b2c9a5"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
