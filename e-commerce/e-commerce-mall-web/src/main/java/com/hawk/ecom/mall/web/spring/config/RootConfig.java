package com.hawk.ecom.mall.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.mall.spring.config.MallRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;


@Configuration
@Import({MallRootConfig.class,SmsRootConfig.class,PubRootConfig.class,DataConfig.class})
public class RootConfig {

	
}
