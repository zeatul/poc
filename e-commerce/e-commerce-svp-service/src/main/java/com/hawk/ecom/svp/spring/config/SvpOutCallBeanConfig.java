package com.hawk.ecom.svp.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hawk.ecom.svp.service.BsiOuterService;
import com.hawk.ecom.svp.service.UnicomService;

@Configuration
public class SvpOutCallBeanConfig {

	@Bean
	public BsiOuterService bsiOuterService() throws Exception{
		/**
		 * 测试环境
		 */
//		String partnercode = "1213623";
//		String key = "id9R4$jsb0";
//		String url = "http://testordersvc.baosm.com/Services/api";
		
		/**
		 * 正式环境
		 */
		String partnercode = "1278147";
		String key = "id933R4$jsb0";
		String url = "http://ordersvc.baosm.com/Services/api";
		
	

		
		BsiOuterService bsiOuterService = new BsiOuterService(partnercode,key,url);
		return bsiOuterService;
	}
	
	@Bean
	public UnicomService unicomService() throws Exception{
		return new UnicomService();
	}
}
