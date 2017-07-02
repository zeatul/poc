package com.hawk.ecom.product.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.ListSkuParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class SkuAdminControllerTest extends AbstractControllerTest {
	private String token = "1cf10efd-000a-4082-8859-adf38d0efe9c";
	
//	@Test
	public void testCreateSku() {
		String url = getUrl("/mall/admin/product/sku/create");
		CreateSkuParam request = new CreateSkuParam();

		request.setCurrency(156);
		request.setMarketPrice(new BigDecimal(100));
		request.setProductId(1000021);
		request.setSalePrice(new BigDecimal(99));
		List<Integer> skuAttrValueIds = new ArrayList<Integer>();
//		skuAttrValueIds.add(10027);//江苏
//		skuAttrValueIds.add(10031); //本地流量
		skuAttrValueIds.add(10029);//广东
		skuAttrValueIds.add(10032); //全国流量
		request.setSkuAttrValueIds(skuAttrValueIds);
		request.setSkuCode(UUID.randomUUID().toString());
		request.setSkuMemo("备注");
		request.setSkuName("充流量 11111xxxx");
		request.setThumbnail("xxxx.jpg");
		

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testListSku() {

		String url = getUrl("/mall/admin/product/sku/list");
		ListSkuParam request = new ListSkuParam();

		request.setIsSpecialPrice(0);
		
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setProductId(1000021);
		request.setSkuStatus(ConstProduct.SkuStatus.EDITING);

		request.setOrder("create_date asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testLoadSku() {
		String url = getUrl("/mall/admin/product/sku/load/id/1010010");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
}
