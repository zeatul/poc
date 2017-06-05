package com.hawk.ecom.svp.controller;

import org.junit.Test;

public class SystemResourceControllerTest extends AbstractControllerTest{

	@Test
	public void testHome(){
		String url = getUrl("/mall/resource/h5main");
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}
}
