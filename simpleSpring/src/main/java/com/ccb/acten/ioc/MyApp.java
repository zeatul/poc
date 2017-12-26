package com.ccb.acten.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {

	public static void main(String[] args) {
		testAop();
	}

	public static void testDeclareBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/applicationContext-beans.xml" });

		Phone phone = context.getBean("phone1", Phone.class);
		System.out.println("brand=" + phone.getBrand() + ",type=" + phone.getType());

		phone = context.getBean("phone2", Phone.class);
		System.out.println("brand=" + phone.getBrand() + ",type=" + phone.getType());

		phone = context.getBean("phone3", Phone.class);
		System.out.println("brand=" + phone.getBrand() + ",type=" + phone.getType());

		phone = context.getBean("phone4", Phone.class);
		System.out.println("brand=" + phone.getBrand() + ",type=" + phone.getType());
	}
	
	public static void testAop(){
		
	}

}
