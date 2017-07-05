package com.hawk.ecom.query.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.query.request.ListSkuParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class ProductControllerTest extends AbstractControllerTest{
//	@Test
	public void testListCategory(){
		String url = getUrl("/ecom/query/product/category/list");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result="+result);
	}
	
//	@Test
	public void testQuerySku(){
		String url = getUrl("/ecom/query/product/sku/list");
		
		ListSkuParam request = new ListSkuParam();
		request.setCategoryId(10017);
		request.setPageIndex(1);
		request.setPageRowCount(2);
		request.setOrder("a.create_date desc");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testLoadSku(){
		String url = getUrl("/ecom/query/product/sku/load/id/1000422");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result="+result);
	}
	
	@Test
	public void testLoadSkuPriceAndQuantity(){
		String url = getUrl("/ecom/query/product/sku/loadSaleData/id/1000422");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result="+result);
	}
}
