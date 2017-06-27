package com.hawk.ecom.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.framework.utility.http.HttpExecutor.HttpParam;

public class EnvironmentControllerTest extends AbstractControllerTest{
	
	
	@Test
	public void testLoadAttrName() {
		String url = getUrl("/mall/admin/product/env/init");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

}
