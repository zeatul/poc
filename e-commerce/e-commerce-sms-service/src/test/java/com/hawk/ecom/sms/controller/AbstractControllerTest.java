package com.hawk.ecom.sms.controller;

import org.junit.Before;

import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;

public class AbstractControllerTest {

	protected HttpExecutor httpExecutor;
	
	private String base = "http://localhost:8080";
	
	protected String getUrl(String path){
//		下表中列出了一些URL特殊符号及编码 十六进制值 
//		1.+ URL 中+号表示空格 %2B 
//		2.空格 URL中的空格可以用+号或者编码 %20 
//		3./ 分隔目录和子目录 %2F 
//		4.? 分隔实际的 URL 和参数 %3F 
//		5.% 指定特殊字符 %25 
//		6.# 表示书签 %23 
//		7.& URL 中指定的参数间的分隔符 %26 
//		8.= URL 中指定参数的值 %3D
		return (base + path).replaceAll(" ", "%20");
	}

	@Before
	public void setup() throws Exception {
		httpExecutor = new HttpClientExecutorImpl();
	}

}
