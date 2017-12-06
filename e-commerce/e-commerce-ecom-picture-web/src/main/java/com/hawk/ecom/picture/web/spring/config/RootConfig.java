package com.hawk.ecom.picture.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.muser.spring.config.MuserRootConfig;
import com.hawk.ecom.picture.spring.config.EcomPictureRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.pub.srping.config.ValidateConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;


@Configuration
@Import({DataConfig.class,MuserRootConfig.class,EcomPictureRootConfig.class,
	SmsRootConfig.class,ValidateConfig.class,PubRootConfig.class})
public class RootConfig {
	
}
