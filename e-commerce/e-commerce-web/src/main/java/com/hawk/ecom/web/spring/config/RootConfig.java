package com.hawk.ecom.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.hawk.ecom.pub.srping.config.PubConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;
import com.hawk.ecom.svp.spring.config.SvpRootConfig;
import com.hawk.ecom.user.spring.config.UserRootConfig;


@Configuration
@Import({SmsRootConfig.class,UserRootConfig.class,SvpRootConfig.class,PubConfig.class,DataConfig.class})
public class RootConfig {

	
}
