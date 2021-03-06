package com.hawk.ecom.pub.srping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.ecom.pub.controller.AppWideExceptionHandler;
import com.hawk.framework.utility.http.HttpExecutor;


@Configuration
@Import({CacheConfig.class,HttpMessageConverterConfig.class,GlobalPkGenConfig.class,ValidateConfig.class})
public class PubRootConfig {

	@Bean
	public FrameworkContext frameworkContext(){
		return new FrameworkContext();
	}
	
	@Bean
	public AppWideExceptionHandler AppWideExceptionHandler(){
		return new AppWideExceptionHandler();
	}
	
	@Bean
	public HttpExecutor HttpExecutor() throws Exception{
		return new HttpClientExecutorImpl();
	}
}
