package com.ccb.acten.aop;

import org.springframework.stereotype.Service;

@Service
public class MyPerformance implements Performance {
	public void perform() {
		System.out.println("My performance is best!!!!");
	}
}
