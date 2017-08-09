package com.hawk.ecom.outer.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hawk.ecom.outer.service.BsiOuterService;
import com.hawk.ecom.outer.service.chargeData.ChargeDataConfigure;


@Configuration
@PropertySource({"classpath:/com/hawk/ecom/outer/env/chargeDataConfigure.properties"
	,"classpath:/com/hawk/ecom/outer/env/bsi.properties"})
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.outer.service" })
public class EcomOuterRootConfig {
	
	@Autowired
	private Environment env;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	
	
	@Bean
	public ChargeDataConfigure chargeDataConfigure(){
		ChargeDataConfigure chargeDataConfigure = new ChargeDataConfigure();
		chargeDataConfigure.setNotifyUrl(env.getProperty("chargeDataConfigure.notifyUrl"));
		logger.info("ChargeDataConfigure,notifyUrl={}",chargeDataConfigure.getNotifyUrl());
		return chargeDataConfigure;
	}
	
	@Bean
	public BsiOuterService bsiOuterService() throws Exception{
//		/**
//		 * 测试环境
//		 */
//		String partnercode = "1213623";
//		String key = "id9R4$jsb0";
//		String url = "http://testordersvc.baosm.com/Services/api";
//		
//		/**
//		 * 正式环境
//		 */
//		String partnercode = "1278147";
//		String key = "id933R4$jsb0";
//		String url = "http://ordersvc.baosm.com/Services/api";
		
		String partnercode = env.getProperty("partnercode");
		String key = env.getProperty("key");
		String url = env.getProperty("url");
	

		logger.info("BsiConfigure partnercode={},url={} ",partnercode,url);
		
		BsiOuterService bsiOuterService = new BsiOuterService(partnercode,key,url);
		return bsiOuterService;
	}
}
