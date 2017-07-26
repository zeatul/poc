package com.hawk.ecom.outer.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.hawk.ecom.outer.service.ChargeDataConfigure;


@Configuration
@PropertySource("classpath:/com/hawk/ecom/outer/env/chargeDataConfigure.properties")
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.outer.service" })
public class EcomOuterRootConfig {
	
	@Autowired
	private Environment env;

	
	
	@Bean
	public ChargeDataConfigure chargeDataConfigure(){
		ChargeDataConfigure chargeDataConfigure = new ChargeDataConfigure();
		chargeDataConfigure.setNotifyUrl(env.getProperty("chargeDataConfigure.notifyUrl"));
		return chargeDataConfigure;
	}
}
