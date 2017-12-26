package com.ccb.acten.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:aop/spring/applicationContext-aop.xml"})
public class TestAop {
	
	@Autowired
	@Qualifier("myPerformance")
	private Performance goodPerformance;
	
	@Autowired
	@Qualifier("myBadPerformance")
	private Performance badPerformance;
	
	@Test
	public void goodPerformance(){
		System.out.println("------------GOOD---------------");
		goodPerformance.perform();
	}
	
	@Test
	public void badPerformance(){
		System.out.println("------------BAD----------------");
		badPerformance.perform();
	}

}
