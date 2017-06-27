package com.hawk.ecom.trans.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.trans.spring.config.EcomTransRootConfig;


@Configuration
@Import({PubRootConfig.class,EcomTransRootConfig.class})
public class RootConfig {

	
}
