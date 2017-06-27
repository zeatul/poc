package com.hawk.ecom.trans.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.trans.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomTransWebConfig extends WebMvcConfigurerAdapter {

}
