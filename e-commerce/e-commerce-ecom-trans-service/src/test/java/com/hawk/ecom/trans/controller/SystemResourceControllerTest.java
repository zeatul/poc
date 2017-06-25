package com.hawk.ecom.query.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hawk.framework.utility.http.HttpExecutor.HttpParam;

public class SystemResourceControllerTest extends AbstractControllerTest{

	@Test
	public void testHome(){
		String url = getUrl("/ecom/query/system/resource/h5main");
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("version", "1.0"));
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}
}
