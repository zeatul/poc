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
	public void testQueryBsiOrderDetail(){
		String bsiCashCouponCode = "755d76d6-fed7-4a8b-9d9e-5294d141ac6f";
		String url = getUrl("/svp/bsi/orderDetail/couponCode/"+bsiCashCouponCode);
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
	
//	@Test
	public void testRegisterPresentCoupon(){
//		https://url?version=1.0&channel=1&store=STO00000001&timestamp=1490921495127&reqhash=5d41402abc4b2a76b9719d911017c592
		RegisterPresentCouponParam registerForCouponParam = new RegisterPresentCouponParam ();
		registerForCouponParam.setMobileNumber("139160824811");
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
	
	@Test
	public void testActivateCoupon(){
		String url =getUrl("/svp/bsi/coupon/activate");
		ActivateCouponParam activateCouponParam =new ActivateCouponParam();
		activateCouponParam.setBirthday("2000-11-07");
		activateCouponParam.setCouponCode("755d76d6-fed7-4a8b-9d9e-5294d141ac6f");
		activateCouponParam.setIdType(ConstBsiIdType.IDENTITY_CARD);
		activateCouponParam.setIdNumber("320109200011071232");
		activateCouponParam.setImei("91891888");
		activateCouponParam.setMobileNumber("139160824811");
		activateCouponParam.setName("黄大仙");
		activateCouponParam.setPhoneModelId(1073);
		activateCouponParam.setProductId(11246);
		activateCouponParam.setSex(ConstBsiSex.MALE);
		activateCouponParam.setTicket("13919082481");
		
		System.out.println("request="+JsonTools.toJsonString(activateCouponParam));
		String result = httpExecutor.post(url, activateCouponParam, null);
		System.out.println("result=" + result);
	}
	
//	@Test
	public void testActivateCouponOnProduct(){
//		String url =getUrl("/svp/bsi/coupon/activate");
		String url = "http://210.73.195.78/ecom/svp/bsi/coupon/activate";

		ActivateCouponParam activateCouponParam =new ActivateCouponParam();
		activateCouponParam.setBirthday("2011-04-04");
		activateCouponParam.setCouponCode("e14975ca-51d2-4a86-b85a-ef64ff135f3b");
		activateCouponParam.setIdType(ConstBsiIdType.IDENTITY_CARD);
		activateCouponParam.setIdNumber("610424198211114992");
		activateCouponParam.setImei("232323232323232");
		activateCouponParam.setMobileNumber("18292417675");
		activateCouponParam.setName("裴明");
		activateCouponParam.setPhoneModelId(104);
		activateCouponParam.setProductId(11240);
		activateCouponParam.setSex(ConstBsiSex.MALE);
		activateCouponParam.setTicket("18292417675");
		
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
	
////	@Test
//	public void testActivateCashCouponJob(){
//		String url =getUrl("/svp/bsi/coupon/job/test");
//		Map<String,String> map = new HashMap<String,String>();
//		String couponCode  = "ae35662b-2760-4e6f-82d9-de7a74ebbc3a";
//		map.put("couponCode",couponCode);
//		
//		String result = httpExecutor.post(url, map, null);
//		System.out.println("result=" + result);
//	}

}
