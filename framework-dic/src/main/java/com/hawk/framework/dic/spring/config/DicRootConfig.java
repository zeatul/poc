package com.hawk.framework.dic.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hawk.framework.dic.service.WordService;
import com.hawk.framework.dic.validation.ValidateService;

@Configuration
@ComponentScan(basePackageClasses = { ValidateService.class,WordService.class })
public class DicRootConfig {

}
