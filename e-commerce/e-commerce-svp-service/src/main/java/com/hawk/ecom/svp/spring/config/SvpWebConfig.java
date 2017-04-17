package com.hawk.ecom.svp.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.hawk.ecom.svp.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class SvpWebConfig extends WebMvcConfigurerAdapter {

}
