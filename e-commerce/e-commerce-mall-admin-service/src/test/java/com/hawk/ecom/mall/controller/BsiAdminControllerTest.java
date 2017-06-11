package com.hawk.ecom.mall.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.hawk.ecom.mall.request.BsiOrderDetailReportParam;
import com.hawk.ecom.mall.request.BsiStatCouponParam;
import com.hawk.ecom.mall.request.BsiStatOrderDetailParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class BsiAdminControllerTest extends AbstractControllerTest {
	
	private String token = "5ee7082b-ef44-4c75-b2a2-9c00ec0785be";
	
//	@Test
	public void testReportOrderDetail(){
		String url = getUrl("/mall/admin/report/svp/bsi/orderDetail");
		
		
		BsiOrderDetailReportParam request = new BsiOrderDetailReportParam();
		request.setStdt(new Date());
		request.setEndt(new Date());
		request.setPageRowCount(100);
		request.setPageIndex(1);
		request.setOrder("create_date desc");
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testStatCoupon(){
		String url = getUrl("/mall/admin/report/svp/bsi/coupon/stat");
		
		BsiStatCouponParam request = new BsiStatCouponParam();
		request.setStdt(new Date());
		request.setEndt(new Date());
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testStatBsiOrderDetail(){
		String url = getUrl("/mall/admin/report/svp/bsi/orderDetail/stat");
		
		BsiStatOrderDetailParam request = new BsiStatOrderDetailParam();
		request.setStdt(new Date());
		request.setEndt(new Date());
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		params.add(new HttpParam("t", token));
		
		System.out.println("request=" + JsonTools.toJsonString(request));
		String result = httpExecutor.post(url, request, params);
		System.out.println("result=" + result);
	}
}
