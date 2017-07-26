package com.hawk.ecom.task.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import com.hawk.ecom.muser.spring.config.MuserRootConfig;
import com.hawk.ecom.product.spring.config.ProductRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;
import com.hawk.ecom.trans.spring.config.EcomTransRootConfig;
import com.hawk.ecom.user.spring.config.UserRootConfig;


@Configuration
@Import({DataConfig.class,UserRootConfig.class,ProductRootConfig.class, SmsRootConfig.class,EcomTransRootConfig.class,PubRootConfig.class,EcomTransRootConfig.class,MuserRootConfig.class})
public class RootConfig {

	
}
