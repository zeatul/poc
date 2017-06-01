package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.mall.constant.ConstSystemResource;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.mall.request.SystemListResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceStatusParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.ecom.mall.request.SystemRemoveResourceParam;

public class SystemResourceControllerTest extends AbstractControllerTest {

	// @Test
	public void testCreate() {
		String url = getUrl("/mall/admin/resource/create");
		SystemCreateResourceParam request = new SystemCreateResourceParam();
		// request.setNodeCode("wts_2");
		request.setNodeDesc("hello");
		request.setNodeName("五台山_111123111");
		request.setNodeSubType(ConstSystemResource.NodeSubType.OTHER);
		request.setNodeType(ConstSystemResource.NodeType.MENU);
		request.setNodeValue("http://");
		request.setNodeValueType(ConstSystemResource.NodeValueType.HTTP);
		request.setNodeIco("100");
		request.setObjectOrder(100);
		request.setParentNodeCode("wts");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	// @Test
	public void testList() {
		String url = getUrl("/mall/admin/resource/list");
		SystemListResourceParam request = new SystemListResourceParam();
		request.setParentNodeCode("root");
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setOrder("object_order asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testLoad() {
		String url = getUrl("/mall/admin/resource/nodeCode/wst");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdate() {
		String url = getUrl("/mall/admin/resource/update");
		SystemUpdateResourceParam request = new SystemUpdateResourceParam();
		request.setNodeCode("wst");
		request.setNodeDesc("hello");
		request.setNodeIco("1111");
		request.setNodeName("nihao");
		request.setNodeValue("https://111.222.cn");
		request.setObjectOrder(1000);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testRemove() {
		String url = getUrl("/mall/admin/resource/remove");
		SystemRemoveResourceParam request = new SystemRemoveResourceParam();
		request.setNodeCodes(Arrays.asList("a","b","c"));
		
		
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testUpdateStatus() {
		String url = getUrl("/mall/admin/status/update");
		SystemUpdateResourceStatusParam request = new SystemUpdateResourceStatusParam();
		request.setNodeCodes(Arrays.asList("a","b","c"));
		request.setNodeStatus(ConstSystemResource.NodeStatus.FORBIDDEN);
		
		
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "24bb9c96-63ea-42a1-ada2-0f35e3505580"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
