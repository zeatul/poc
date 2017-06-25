package com.hawk.ecom.query.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.query.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomQueryWebConfig extends WebMvcConfigurerAdapter {

}
