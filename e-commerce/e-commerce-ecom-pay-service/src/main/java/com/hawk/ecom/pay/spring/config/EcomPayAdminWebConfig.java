package com.hawk.ecom.pay.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.pay.controller.admin.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomPayAdminWebConfig extends WebMvcConfigurerAdapter {

}
