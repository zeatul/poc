package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.mall.request.SystemExchangeResourceOrderParam;
import com.hawk.ecom.mall.request.SystemListResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceStatusParam;
import com.hawk.ecom.pub.constant.ConstSystemResource;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.ecom.mall.request.SystemRemoveResourceParam;

public class SystemResourceControllerTest extends AbstractControllerTest {

	private String token = "d2b4872f-4842-4128-b387-fb63e5e7763c";

	 @Test
	public void testCreate() {
		String url = getUrl("/mall/admin/resource/create");
		SystemCreateResourceParam request = new SystemCreateResourceParam();
		// request.setNodeCode("wts_2");
		request.setNodeDesc("hello");
		request.setNodeName("上海");
		request.setNodeSubType(ConstSystemResource.NodeSubType.OTHER);
		request.setNodeType(ConstSystemResource.NodeType.MENU);
		request.setNodeValue("http://beijing.com");
		request.setNodeValueType(ConstSystemResource.NodeValueType.HTTP);
		request.setNodeIco("100");
		// request.setObjectOrder(100);
		request.setParentNodeCode("h5main");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	 @Test
	public void testList() {
		String url = getUrl("/mall/admin/resource/list");
		SystemListResourceParam request = new SystemListResourceParam();
		request.setParentNodeCode("h5main");
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setOrder("object_order asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testLoad() {
		String url = getUrl("/mall/admin/resource/nodeCode/h5main");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testUpdate() {
		String url = getUrl("/mall/admin/resource/update");
		SystemUpdateResourceParam request = new SystemUpdateResourceParam();
		request.setNodeCode("10005");
		request.setNodeDesc("beautiful");
		request.setNodeIco("1112");
		request.setNodeName("南京");
		request.setNodeValue("https://nanjing.com");
		request.setObjectOrder(1);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	 @Test
	public void testRemove() {
		String url = getUrl("/mall/admin/resource/remove");
		SystemRemoveResourceParam request = new SystemRemoveResourceParam();
		request.setNodeCodes(Arrays.asList("10001", "10005"));

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	 @Test
	public void testUpdateStatus() {
		String url = getUrl("/mall/admin/resource/status/update");
		SystemUpdateResourceStatusParam request = new SystemUpdateResourceStatusParam();
		request.setNodeCodes(Arrays.asList("10003", "10004"));
		request.setNodeStatus(ConstSystemResource.NodeStatus.ACTIVATED);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	 @Test
	public void testOrderExchange() {
		String url = getUrl("/mall/admin/resource/order/exchange");
		SystemExchangeResourceOrderParam request = new SystemExchangeResourceOrderParam();
		request.setNodeCodeA("100001");
		request.setNodeCodeB("100002");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
