package com.ccb.acten.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAopApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext
				(new String[] { "classpath:aop/spring/applicationContext-aop.xml" });
		Performance performance = (Performance) context.getBean("myPerformance");
		performance.perform();
		System.out.println("--------------------------------------------------------");
		performance = (Performance) context.getBean("myBadPerformance");
		performance.perform();
	}

}
