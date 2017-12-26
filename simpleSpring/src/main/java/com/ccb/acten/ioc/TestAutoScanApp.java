package com.ccb.acten.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAutoScanApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/applicationContext-beans4.xml" });	
		AutoCreatePhoneFactory autoCreatePhoneFactory = context.getBean(AutoCreatePhoneFactory.class);
		System.out.println(autoCreatePhoneFactory);
	}

}
