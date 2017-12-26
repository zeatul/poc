package com.ccb.acten.aop;

import org.springframework.stereotype.Component;

@Component
public class MyBadPerformance implements Performance {
	public void perform() {
		System.out.println("My performance is so bad !!!!");
		throw new RuntimeException("My performance is so bad !!!!");		
	}
}

