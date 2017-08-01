package com.hawk.ecom.query.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.query.request.LoadChargeDataProductParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MobileControllerTest extends AbstractControllerTest {

//	 @Test
	public void testLoadMobileNumberSegment() {
		String url = getUrl("/ecom/query/mobile/segment/mobileNumber/13311658157");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	@Test
	public void testLoadChargeDataProduct() {
		String url = getUrl("/ecom/query/mobile/sku/chargeData/mobileNumber/13311658157/regionType/all");
		
		
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

}
