package com.hawk.ecom.query.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.query.spring.config.EcomQueryRootConfig;


@Configuration
@Import({PubRootConfig.class,EcomQueryRootConfig.class})
public class RootConfig {

	
}
