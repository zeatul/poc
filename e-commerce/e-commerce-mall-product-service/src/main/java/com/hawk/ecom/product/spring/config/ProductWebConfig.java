package com.hawk.ecom.product.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.product.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class ProductWebConfig extends WebMvcConfigurerAdapter {

}
