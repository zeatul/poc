package com.hawk.ecom.pub.srping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Configuration
@Import({HttpMessageConverterConfig.class,GlobalPkGenConfig.class})
public class PubConfig {

}
