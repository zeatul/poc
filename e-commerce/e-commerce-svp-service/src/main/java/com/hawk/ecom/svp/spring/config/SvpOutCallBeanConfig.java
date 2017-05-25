package com.hawk.ecom.svp.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.hawk.ecom.svp.service.BsiOuterService;
import com.hawk.ecom.svp.service.UnicomService;

@Configuration
@PropertySource("classpath:/com/hawk/ecom/svp/env/bsi.properties")
public class SvpOutCallBeanConfig {
	
	@Autowired
	private Environment env;

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
	

		
		BsiOuterService bsiOuterService = new BsiOuterService(partnercode,key,url);
		return bsiOuterService;
	}
	
	@Bean
	public UnicomService unicomService() throws Exception{
		return new UnicomService();
	}
}
