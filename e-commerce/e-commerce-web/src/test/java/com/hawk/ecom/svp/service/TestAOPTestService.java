package com.hawk.ecom.svp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.svp.service.AOPTestService.ComplexParam;
import com.hawk.framework.dic.exception.EmptyParameterRuntimeException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PubRootConfig.class)
public class TestAOPTestService {
	
	@Autowired
	private AOPTestService aopTestService;
	
//	@Test
	public void test() throws EmptyParameterRuntimeException{
		aopTestService.exec("");
	}
	
	@Test
	public void test2() throws EmptyParameterRuntimeException {
		ComplexParam param = new ComplexParam();
		param.setMobilePhone("133");
		aopTestService.exec(param);
	}

}
