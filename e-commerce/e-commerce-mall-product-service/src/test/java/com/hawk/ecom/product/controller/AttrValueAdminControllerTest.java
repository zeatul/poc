package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstAttr;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.request.ListAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueStatusParam;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class AttrValueAdminControllerTest extends AbstractControllerTest{
	
	private String token = "fe3a4813-4f71-48e1-abf1-2f708ef9fe55";
	
//	@Test
	public void testCreateAttrValue(){
		
		String url = getUrl("/mall/admin/product/attr/value/create");
		CreateAttrValueParam request = new CreateAttrValueParam();

		request.setAttrDisplayValue("华硕");
		request.setAttrNameId(10005);
		request.setAttrValue("华硕");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
		
	}

//	@Test
	public void testListAttrValue(){
		String url = getUrl("/mall/admin/product/attr/value/list");
		ListAttrValueParam request = new ListAttrValueParam();

		request.setAttrNameId(10005);
		request.setAttrValueStatus(ConstAttr.AttrValueStatus.AVAIlABLE);
		request.setCategoryId(10001);
		request.setOrder("create_date desc");
		request.setPageIndex(1);
		request.setPageRowCount(100);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testLoadAttrValue(){
		String url = getUrl("/mall/admin/product/attr/value/load/id/10006");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdateAttrValue(){
		String url = getUrl("/mall/admin/product/attr/value/update");
		UpdateAttrValueParam request = new UpdateAttrValueParam();

		request.setAttrDisplayValue("IBM");
		request.setAttrValue("IBM");
		request.setId(10006);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testUpdateAttrValueStatus(){
		String url = getUrl("/mall/admin/product/attr/value/status/update");
		UpdateAttrValueStatusParam request = new UpdateAttrValueStatusParam();
		request.setAttrValueStatus(ConstAttr.AttrValueStatus.FORBIDDEN);		
		request.setIds(Arrays.asList(10006));
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
