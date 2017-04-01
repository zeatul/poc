package com.hawk.ecom.svp.controller;

import java.net.URLEncoder;

import org.junit.Test;

import com.hawk.ecom.svp.constant.ConstBsiIdType;
import com.hawk.ecom.svp.constant.ConstBsiSex;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.RegisterPresentCouponParam;
import com.hawk.framework.utility.tools.JsonTools;

public class BsiControllerTest extends AbstractControllerTest {

//	@Test
	public void testHome() {
		String url = getUrl("/svp/bsi/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testBrand(){
		String url = getUrl("/svp/bsi/brand");
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testModelOfBrand(){
		String url = getUrl("/svp/bsi/brand/" + URLEncoder.encode("魅族"));
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
	
	
//	@Test
	public void testQueryProduct(){
		String url =getUrl( "/svp/bsi/modelId/1201/period/1");
	
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
		
	}
	
//	@Test
	public void testRegisterPresentCoupon(){
		String url =getUrl("/svp/bsi/coupon/register/present");
		RegisterPresentCouponParam registerForCouponParam = new RegisterPresentCouponParam ();
		registerForCouponParam.setMobileNumber("13916082487");
		String result = httpExecutor.post(url, registerForCouponParam, null);
		System.out.println("result=" + result);
	}
	
	@Test
	public void testActivateCoupon(){
		String url =getUrl("/svp/bsi/coupon/activate");
		ActivateCouponParam activateCouponParam =new ActivateCouponParam();
		activateCouponParam.setBirthday("2000-11-07");
		activateCouponParam.setCouponCode("7b1187db-6ddd-4b62-9e3a-9d588167cf42");
		activateCouponParam.setIdType(ConstBsiIdType.IDENTITY_CARD);
		activateCouponParam.setIdNumber("320109200011071232");
		activateCouponParam.setImei("123456");
		activateCouponParam.setMobileNumber("13311658138");
		activateCouponParam.setName("黄大仙");
		activateCouponParam.setPhoneModelId(200);
		activateCouponParam.setProductId(10260);
		activateCouponParam.setSex(ConstBsiSex.MALE);
		activateCouponParam.setTicket("13916082487");
		
		System.out.println("request="+JsonTools.toJsonString(activateCouponParam));
		String result = httpExecutor.post(url, activateCouponParam, null);
		System.out.println("result=" + result);
	}

}
