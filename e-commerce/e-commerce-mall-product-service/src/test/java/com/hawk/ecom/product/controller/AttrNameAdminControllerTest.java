package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.ListAttrNameParam;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class AttrNameAdminControllerTest extends AbstractControllerTest {

	private String token = "2b48c6ca-a24e-4c2e-932e-8b6756785605";

	// @Test
	public void testCreateAttrName() {

		String url = getUrl("/mall/admin/product/attr/name/create");
		CreateAttrNameParam request = new CreateAttrNameParam();

		request.setAttrName("内存");
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

	// @Test
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

	@Test
	public void testLoadAttrName() {
		String url = getUrl("/mall/admin/product/attr/name/load/id/10002");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

}
