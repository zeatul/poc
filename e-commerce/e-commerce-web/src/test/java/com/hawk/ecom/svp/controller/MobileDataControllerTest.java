package com.hawk.ecom.svp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.hawk.ecom.svp.request.SignInParam;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.JsonTools;

public class MobileDataControllerTest extends AbstractControllerTest{
	
	@Test
	public void testSignIn(){
		SignInParam signInParam = new SignInParam ();
		signInParam.setMobileNumber("13916082487");
		String req = JsonTools.toJsonString(signInParam);
		
		String url =getUrl("/svp/mobile/signin");
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

}
