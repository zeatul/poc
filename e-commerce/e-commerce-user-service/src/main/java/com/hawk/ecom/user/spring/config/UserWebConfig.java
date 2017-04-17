package com.hawk.ecom.user.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.hawk.ecom.user.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class UserWebConfig extends WebMvcConfigurerAdapter {

}
