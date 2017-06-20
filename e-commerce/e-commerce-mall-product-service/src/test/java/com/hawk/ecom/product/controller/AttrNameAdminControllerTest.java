package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class AttrNameAdminControllerTest extends AbstractControllerTest{
	
	private String token = "c778247b-4959-42a5-9f8a-e0060d256832";
	
	@Test
	public void testCreateAttrName(){
		
		String url = getUrl("/mall/admin/product/attr/name/create");
		CreateAttrNameParam request = new CreateAttrNameParam();

		request.setAttrName("品牌");
		request.setAttrNameBusinessType(ConstAttr.AttrNameBusinessType.BRAND);
		request.setAttrValueType(ConstAttr.AttrValueType.STRING);
		request.setCategoryId(10007);
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

}
