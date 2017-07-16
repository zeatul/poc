package com.hawk.ecom.task.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.task.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomTaskWebConfig extends WebMvcConfigurerAdapter {

}
