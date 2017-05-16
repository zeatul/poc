package com.hawk.ecom.mall.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;


@Configuration
@Import({SmsRootConfig.class,PubRootConfig.class,DataConfig.class})
public class RootConfig {

	
}
