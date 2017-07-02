package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class ProductAdminControllerTest extends AbstractControllerTest {
	private String token = "84dba44d-34c9-46a7-be94-5aae203dc71b";

//	@Test
	public void testCreateProduct() {
		String url = getUrl("/mall/admin/product/product/create");
		CreateProductParam request = new CreateProductParam();

		request.setCategoryId(10017);
		request.setDeliveryType(ConstProduct.DeliveryType.CHARGE_FLOW_DATA);
		request.setIsVirtual(1);
		request.setProductCode("charge 1111");
		request.setProductDesc("测试创建流量充值商品");
		request.setProductHomePage("http://www.google.com.cn");
		List<Integer> productKeyAttrValueIds = new ArrayList<Integer>();
		productKeyAttrValueIds.add(10019); // 电信
		productKeyAttrValueIds.add(10025); // 5000M
		request.setProductKeyAttrValueIds(productKeyAttrValueIds);
		request.setProductMemo("hello world");
		request.setProductName("流量充值产品1号");
		request.setProductNormalAttrValueIds(null);
		List<Integer> productSkuAttrNameIds = new ArrayList<Integer>();
		productSkuAttrNameIds.add(10026); // 地区
		productSkuAttrNameIds.add(10030); // 流量类型
		request.setProductSkuAttrNameIds(productSkuAttrNameIds);
		request.setThumbnail("xxxx.jpg");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testListProduct() {

		String url = getUrl("/mall/admin/product/product/list");
		ListProductParam request = new ListProductParam();

		request.setCategoryId(10017);
		request.setIsVirtual(1);
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setProductStatus(ConstProduct.ProductStatus.EDITING);
		request.setDeliveryType(ConstProduct.DeliveryType.CHARGE_FLOW_DATA);

		request.setOrder("create_date asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testLoadProduct() {
		String url = getUrl("/mall/admin/product/product/load/id/1000021");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
}
