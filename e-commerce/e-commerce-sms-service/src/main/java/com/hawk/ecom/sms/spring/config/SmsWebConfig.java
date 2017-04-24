package com.hawk.ecom.sms.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.sms.controller.SmsController;

@Configuration
@ComponentScan(basePackageClasses = { SmsController.class })
public class SmsWebConfig extends WebMvcConfigurerAdapter {

}
