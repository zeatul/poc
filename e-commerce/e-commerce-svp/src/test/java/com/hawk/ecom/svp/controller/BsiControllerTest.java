package com.hawk.ecom.svp.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.hawk.ecom.svp.constant.ConstBsiIdType;
import com.hawk.ecom.svp.constant.ConstBsiSex;
import com.hawk.ecom.svp.request.ActivateCouponParam;
import com.hawk.ecom.svp.request.ListCouponParam;
import com.hawk.ecom.svp.request.RegisterPresentCouponParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
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
	public void testModelOfBrand() throws UnsupportedEncodingException{
		String url = getUrl("/svp/bsi/brand/" + URLEncoder.encode("魅族","utr-8"));
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
	}
	
	
	
//	@Test
	public void testQueryProduct(){
		String url =getUrl( "/svp/bsi/modelId/1201/period/1");
	
		String result = httpExecutor.get(url, null);
		System.out.println("result=" + result);
		
	}
	
	@Test
	public void testRegisterPresentCoupon(){
//		https://url?version=1.0&channel=1&store=STO00000001&timestamp=1490921495127&reqhash=5d41402abc4b2a76b9719d911017c592
		RegisterPresentCouponParam registerForCouponParam = new RegisterPresentCouponParam ();
		registerForCouponParam.setMobileNumber("13916082487");
		String req = JsonTools.toJsonString(registerForCouponParam);
		
		String url =getUrl("/svp/bsi/coupon/register/present");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version","1.0"));
		params.add(new HttpParam("channel","0"));
		params.add(new HttpParam("store","STO00000001"));
		String timestamp = new Long(new Date().getTime()).toString();
		params.add(new HttpParam("timestamp",timestamp));
		String key = "&%12a9H6@";
		String reqhash =DigestUtils.md5Hex( req +timestamp+key);
		params.add(new HttpParam("reqhash",reqhash));
		
		String result = httpExecutor.post(url, req, params);
		System.out.println("result=" + result);
	}
	
//	@Test
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
	
//	@Test
	public void testListCoupon(){
		String url =getUrl("/svp/bsi/coupon/list");
		ListCouponParam listCouponParam = new ListCouponParam();
		listCouponParam.setMobileNumber("13916082487");
		System.out.println("request="+JsonTools.toJsonString(listCouponParam));
		String result = httpExecutor.post(url, listCouponParam, null);
		System.out.println("result=" + result);
	}

}
