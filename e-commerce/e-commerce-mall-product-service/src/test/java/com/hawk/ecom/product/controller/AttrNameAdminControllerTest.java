package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.ListAttrNameParam;
import com.hawk.ecom.product.request.RemoveAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameStatusParam;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class AttrNameAdminControllerTest extends AbstractControllerTest {

	private String token = "fe3a4813-4f71-48e1-abf1-2f708ef9fe55";

	 @Test
	public void testCreateAttrName() {

		String url = getUrl("/mall/admin/product/attr/name/create");
		CreateAttrNameParam request = new CreateAttrNameParam();

		request.setAttrName("品牌");
		request.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.BRAND);
		request.setAttrValueType(ConstAttr.AttrValueType.STRING);
		request.setCategoryId(10001);
		request.setIsSearch(ConstBoolean.TRUE);
		request.setPid(0);
		request.setPvid(0);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);

	}

//	 @Test
	public void testListAttrName() {

		String url = getUrl("/mall/admin/product/attr/name/list");
		ListAttrNameParam request = new ListAttrNameParam();

		request.setCategoryId(10001);
		// request.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.BRAND);
		// request.setAttrValueType(ConstAttr.AttrValueType.STRING);
		// request.setAttrNameStatus(ConstAttr.AttrNameStatus.AVAIlABLE);
		// request.setPid(0);
		// request.setPvid(0);
		// request.setIsSearch(ConstBoolean.TRUE);
		// request.setPageIndex(1);
		// request.setPageRowCount(100);
		request.setOrder("create_date asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testLoadAttrName() {
		String url = getUrl("/mall/admin/product/attr/name/load/id/10002");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdateAttrName(){
		String url = getUrl("/mall/admin/product/attr/name/update");
		UpdateAttrNameParam request = new UpdateAttrNameParam();

		request.setAttrName("内存1");		
		request.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.BRAND);
		request.setAttrValueType(ConstAttr.AttrValueType.STRING);
		request.setIsSearch(ConstBoolean.TRUE);
		request.setId(10002);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test 
	public void testRemoveAttrName(){
		String url = getUrl("/mall/admin/product/attr/name/remove");
		RemoveAttrNameParam request = new RemoveAttrNameParam();
		
		request.setIds(Arrays.asList(10002));

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testUpdateAttrNameStatus(){
		String url = getUrl("/mall/admin/product/attr/name/status/update");
		UpdateAttrNameStatusParam request = new UpdateAttrNameStatusParam();

		request.setAttrNameStatus(ConstAttr.AttrNameStatus.FORBIDDEN);
		request.setIds(Arrays.asList(10003));

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
