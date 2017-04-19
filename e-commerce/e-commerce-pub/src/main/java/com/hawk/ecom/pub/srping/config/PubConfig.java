package com.hawk.ecom.pub.srping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.ecom.pub.controller.AppWideExceptionHandler;


@Configuration
@Import({HttpMessageConverterConfig.class,GlobalPkGenConfig.class,ValidateConfig.class})
public class PubConfig {

	@Bean
	public FrameworkContext frameworkContext(){
		return new FrameworkContext();
	}
	
	@Bean
	public AppWideExceptionHandler AppWideExceptionHandler(){
		return new AppWideExceptionHandler();
	}
}
