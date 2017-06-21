package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.hawk.ecom.product.constant.ConstCategory;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.ExchangeCategoryOrderParam;
import com.hawk.ecom.product.request.ListCategoryParam;
import com.hawk.ecom.product.request.RemoveCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryTemplateStatusParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class CategoryAdminControllerTest extends AbstractControllerTest {

	private String token = "53ab62cb-1bef-46ab-a186-540dc554c2df";

//	@Test
	public void testCreateCategory() {
		String url = getUrl("/mall/admin/product/category/create");
		CreateCategoryParam request = new CreateCategoryParam();

		request.setCategoryCode(UUID.randomUUID().toString());
		request.setCategoryDesc("desc");
		request.setCategoryHomePage("http://apple");
		request.setCategoryLogo("http://apple/logo");
		request.setCategoryName("笔记本电脑");
		request.setIsLeaf(1);
//		request.setObjectOrder();
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
		String url = getUrl("/mall/admin/product/category/load/id/10001");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testRemoveCategory() {
		String url = getUrl("/mall/admin/product/category/remove");
		RemoveCategoryParam request = new RemoveCategoryParam();
		request.setIds(Arrays.asList(10005,10006));
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testUpdateCategory(){
		String url = getUrl("/mall/admin/product/category/update");
		UpdateCategoryParam request = new UpdateCategoryParam();
		request.setId(10007);
		request.setCategoryDesc("grape.desc");
		request.setCategoryHomePage("http://grape/home");
		request.setCategoryLogo("http://grape/logo");
		request.setCategoryName("葡萄");
		request.setObjectOrder(200);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);	
	}
	
//	@Test
	public void testUpdateCategoryStatus(){
		String url = getUrl("/mall/admin/product/category/template/status/update");
		UpdateCategoryTemplateStatusParam request = new UpdateCategoryTemplateStatusParam();
		request.setIds(Arrays.asList(10007));
		request.setCategoryTemplateStatus(ConstCategory.CategoryTemplateStatus.EDITING);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);	
	}
	
//	@Test
	public void testListCategory(){
		String url = getUrl("/mall/admin/product/category/list");
		ListCategoryParam request = new ListCategoryParam();
		request.setPid(10001);
//		request.setIsLeaf(0);
//		request.setCategoryStatus(ConstCategory.CategoryStatus.AVAILABLE);
//		request.setCategoryTemplateStatus(ConstCategory.CategoryTemplateStatus.EDITING);
		request.setPageIndex(1);
		request.setPageRowCount(100);
		request.setOrder("object_order asc");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);	
	}
	
	@Test
	public void testExchangeCategoryOrder(){
		String url = getUrl("/mall/admin/product/category/order/exchange");
		ExchangeCategoryOrderParam request = new ExchangeCategoryOrderParam();
		request.setCategoryIdA(10007);
		request.setCategoryIdB(10008);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);	
	}
}
