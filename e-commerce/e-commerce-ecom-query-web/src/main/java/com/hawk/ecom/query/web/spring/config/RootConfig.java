package com.hawk.ecom.query.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.base.spring.config.EcomBaseRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.query.spring.config.EcomQueryRootConfig;


@Configuration
@Import({PubRootConfig.class,EcomQueryRootConfig.class,EcomBaseRootConfig.class})
public class RootConfig {

	
}
