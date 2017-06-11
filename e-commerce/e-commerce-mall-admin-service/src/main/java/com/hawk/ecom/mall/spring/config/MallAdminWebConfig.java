package com.hawk.ecom.mall.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.hawk.ecom.mall.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class MallAdminWebConfig extends WebMvcConfigurerAdapter {

}
