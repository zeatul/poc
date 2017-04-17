package com.hawk.ecom.svp.controller;

import org.junit.Test;

public class HomeControllerTest extends AbstractControllerTest{
	
	
	@Test
	public void testHome(){
		String url = getUrl("/home");
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}
	
	@Test
	public void testError(){
		String url = getUrl("/error");
		String result = httpExecutor.get(url, null);
		System.out.println("result="+result);
	}

}
