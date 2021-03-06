package com.hawk.ecom.mall.web.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.hawk.ecom.mall.spring.config.MallAdminRootConfig;
import com.hawk.ecom.muser.spring.config.MuserRootConfig;
import com.hawk.ecom.outer.spring.config.EcomOuterRootConfig;
import com.hawk.ecom.pay.spring.config.EcomPayRootConfig;
import com.hawk.ecom.product.spring.config.ProductRootConfig;
import com.hawk.ecom.pub.srping.config.PubRootConfig;
import com.hawk.ecom.sms.spring.config.SmsRootConfig;
import com.hawk.ecom.trans.spring.config.EcomTransRootConfig;

@Configuration
@Import({ DataConfig.class,EcomOuterRootConfig.class,EcomPayRootConfig.class, EcomTransRootConfig.class,MallAdminRootConfig.class, MuserRootConfig.class, SmsRootConfig.class, PubRootConfig.class, ProductRootConfig.class })
public class RootConfig {

}
