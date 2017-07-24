package com.hawk.ecom.trans.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.muser.spring.config.MuserRootConfig;
import com.hawk.ecom.outer.spring.config.EcomOuterRootConfig;
import com.hawk.ecom.pay.spring.config.EcomPayRootConfig;
import com.hawk.ecom.product.spring.config.ProductRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;
import com.hawk.ecom.trans.spring.config.EcomTransRootConfig;
import com.hawk.ecom.user.spring.config.UserRootConfig;


@Configuration
@Import({EcomOuterRootConfig.class,PubRootConfig.class,EcomTransRootConfig.class,UserRootConfig.class,
	SmsRootConfig.class,ProductRootConfig.class,MuserRootConfig.class,EcomPayRootConfig.class})
public class RootConfig {

	
}
