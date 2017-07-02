package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.ecom.product.request.UpdateProductParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class ProductAdminControllerTest extends AbstractControllerTest {
	private String token = "1cf10efd-000a-4082-8859-adf38d0efe9c";

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
	
//	@Test
	public void testLoadProduct() {
		String url = getUrl("/mall/admin/product/product/load/id/1000021");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testUpdateProduct() {
		String url = getUrl("/mall/admin/product/product/update");
		UpdateProductParam request = new UpdateProductParam();
		List<Integer> addKeyAttrValueIds = new ArrayList<Integer>();
		addKeyAttrValueIds.add(10028);
		request.setAddKeyAttrValueIds(addKeyAttrValueIds);
		List<Integer> addSkuAttrNameIds = new ArrayList<Integer>();
		addSkuAttrNameIds.add(10022);
		request.setAddSkuAttrNameIds(addSkuAttrNameIds);
		request.setId(1000021);
		request.setProductCode("code改");
		request.setProductDesc("desc改");
		request.setProductHomePage("http://www.sina.com.cn");
		request.setProductMemo("memo改");
		request.setProductName("流量充值改");
		List<Integer> removeKeyAttrValueIds = new ArrayList<Integer>();
		removeKeyAttrValueIds.add(10025);
		request.setRemoveKeyAttrValueIds(removeKeyAttrValueIds);
		List<Integer> removeSkuAttrNameIds = new ArrayList<Integer>();
		removeSkuAttrNameIds.add(10026);
		request.setRemoveSkuAttrNameIds(removeSkuAttrNameIds);
		request.setThumbnail("xxx改.jpg");

		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
