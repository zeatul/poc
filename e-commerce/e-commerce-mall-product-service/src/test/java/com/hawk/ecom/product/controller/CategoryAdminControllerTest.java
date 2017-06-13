package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.ListSubCategoryParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class CategoryAdminControllerTest extends AbstractControllerTest {

	private String token = "c3710d8e-2bee-4d8d-a10f-c75ac6d62388";

	 @Test
	public void testCreateCategory() {
		String url = getUrl("/mall/admin/product/category/create");
		CreateCategoryParam request = new CreateCategoryParam();

		request.setCategoryCode(null);
		request.setCategoryDesc("desc");
		request.setCategoryHomePage("http://home");
		request.setCategoryLogo("http://logo");
		request.setCategoryName("裙子");
		request.setIsLeaf(0);
		request.setObjectOrder(null);
		request.setPid(1100003l);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	 @Test
	public void testListSubCategory() {
		String url = getUrl("/mall/admin/product/category/listSub");
		ListSubCategoryParam request = new ListSubCategoryParam();
		request.setOrder("object_order asc");
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setPid(0);

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

//	@Test
	public void testLoadCategory() {
		String url = getUrl("/mall/admin/product/category/id/1100001");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
}
