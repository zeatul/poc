package com.hawk.ecom.svp.controller;

import java.net.URLEncoder;

import org.junit.Test;

import com.hawk.ecom.svp.request.RegisterForCouponParam;

public class BsiControllerTest extends AbstractControllerTest {

	@Test
	public void testHome() {
		String url = getUrl("/svp/bsi/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testBrand(){
		String url = getUrl("/svp/bsi/brand");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testModelOfBrand(){
		String url = getUrl("/svp/bsi/brand");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
	
	
	@Test
	public void testQueryProduct(){
		String url =getUrl( "/svp/bsi/brand/" + URLEncoder.encode("联想")+"/model/"+ URLEncoder.encode("PHAB Plus")+"/period/3");
	
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
		
	}
	
	@Test
	public void testRegisterForCoupon(){
		String url =getUrl("/svp/bsi/coupon/register/present");
		RegisterForCouponParam registerForCouponParam = new RegisterForCouponParam ();
		registerForCouponParam.setMobileNumber("13916082483");
		String result = httpExecutor.post(url, registerForCouponParam, null);
		System.out.println("result=" + result);
	}

}
