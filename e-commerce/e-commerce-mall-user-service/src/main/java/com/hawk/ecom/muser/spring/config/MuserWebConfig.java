package com.hawk.ecom.muser.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.hawk.ecom.muser.controller.HomeController;

@Configuration
@ComponentScan(basePackageClasses = { HomeController.class })
public class MuserWebConfig extends WebMvcConfigurerAdapter {

}
