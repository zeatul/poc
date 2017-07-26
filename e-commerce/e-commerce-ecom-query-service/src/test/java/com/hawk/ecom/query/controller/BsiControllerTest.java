package com.hawk.ecom.query.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.framework.utility.http.HttpExecutor.HttpParam;

public class BsiControllerTest extends AbstractControllerTest {

//	@Test
	public void testBrand() {
		String url = getUrl("/ecom/query/bsi/phone/brand");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

	@Test
	public void testModelOfBrand() throws UnsupportedEncodingException {
		String url = getUrl("/ecom/query/bsi/phone/model/brand/"+URLEncoder.encode("魅族"));
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, params);
		System.out.println("result=" + result);
	}

}
