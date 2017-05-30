package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.mall.constant.ConstSystemResource;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class SystemResourceControllerTest extends AbstractControllerTest{
	
	@Test
	public void testCreate(){
		String url = getUrl("/mall/admin/resource/create");
		SystemCreateResourceParam request = new SystemCreateResourceParam();
		request.setNodeCode("wts");
		request.setNodeDesc("hello");
		request.setNodeName("五台山");
		request.setNodeSubType(ConstSystemResource.NodeSubType.OTHER);
		request.setNodeType(ConstSystemResource.NodeType.MENU);
		request.setNodeValue("http://");
		request.setNodeValueType(ConstSystemResource.NodeValueType.HTTP);
		request.setNodeIco("100");
		request.setObjectOrder(100);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", "dd012a7e-33d6-4c91-ba6e-c06d39c4e929"));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

}
