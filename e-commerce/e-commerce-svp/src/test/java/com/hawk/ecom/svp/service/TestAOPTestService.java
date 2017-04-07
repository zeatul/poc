package com.hawk.ecom.svp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hawk.ecom.svp.spring.config.SvpAopConfig;
import com.hawk.framework.dic.validation.ValidateException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SvpAopConfig.class)
public class TestAOPTestService {
	
	@Autowired
	private AOPTestService aopTestService;
	
	@Test
	public void test() throws ValidateException{
		aopTestService.exec(null);
	}

}
