package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.hawk.ecom.product.constant.ConstProduct;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.product.request.ListStockParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class StockAdminControllerTest extends AbstractControllerTest {
	private String token = "1cf10efd-000a-4082-8859-adf38d0efe9c";

	// @Test
	public void testCreateStock() {
		String url = getUrl("/mall/admin/product/stock/create");
		CreateStockParam request = new CreateStockParam();

		request.setSkuId(1010007);
		request.setStockItemCode("KPM1111");
		request.setStockMemo("测试");
		request.setStockOperation(ConstProduct.StockOperation.STOCK_IN);
		request.setWarehouseCode("UnKnown");
		request.setStockQuantity(3000);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

	@Test
	public void testListStock() {

		String url = getUrl("/mall/admin/product/stock/list");
		ListStockParam request = new ListStockParam();

		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setProductId(1000021);
		request.setSkuId(1010007);
		request.setStockOperation(ConstProduct.StockOperation.STOCK_IN);
		request.setOrder("create_date asc");

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
