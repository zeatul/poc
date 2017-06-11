package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import com.hawk.ecom.muser.request.MallAddRoleMembersParam;
import com.hawk.ecom.muser.request.MallListRoleMembersParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MallRoleControllerTest extends AbstractControllerTest {

	
	
//	@Test
	public void testAddRoleMembers(){
		String url = getUrl("/mall/admin/role/member/add");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		
		MallAddRoleMembersParam request = new MallAddRoleMembersParam();
		request.setRoleCode("admin");
		request.setUserCodes(Arrays.asList("100001","100002","100003"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testListRoleMembers(){
		String url = getUrl("/mall/admin/role/member/list");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		
		MallListRoleMembersParam request = new MallListRoleMembersParam();
		request.setOrder("c.create_date desc");
		request.setPageIndex(1);
		request.setPageRowCount(21);
		request.setRoleCode("admin");
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testRemoveRoleMembers(){
		String url = getUrl("/mall/admin/role/member/remove");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "c6618ace-5d19-48f6-affb-e4174b0c9cec"));
		
		MallAddRoleMembersParam request = new MallAddRoleMembersParam();
		request.setRoleCode("admin");
		request.setUserCodes(Arrays.asList("100001"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
