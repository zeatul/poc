package com.hawk.ecom.outer.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.outer.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomOuterWebConfig extends WebMvcConfigurerAdapter {

}
