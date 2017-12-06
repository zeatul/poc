package com.hawk.ecom.picture.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.picture.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomPictureWebConfig extends WebMvcConfigurerAdapter {

}
