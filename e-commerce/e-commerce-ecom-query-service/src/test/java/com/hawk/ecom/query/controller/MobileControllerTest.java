package com.hawk.ecom.query.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.query.request.LoadChargeDataProductParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MobileControllerTest extends AbstractControllerTest {

	// @Test
	public void testLoadMobileNumberSegment() {
		String url = getUrl("/ecom/query/mobile/segment/mobileNumber/13311658157");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	@Test
	public void testLoadChargeDataProduct() {
		String url = getUrl("/ecom/query/mobile/sku/chargeData");
		
		LoadChargeDataProductParam request = new LoadChargeDataProductParam();
		request.setOperator("unicom");
		request.setProvince("310000");
		request.setRegionType("province");
		request.setSize(100);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}

}
