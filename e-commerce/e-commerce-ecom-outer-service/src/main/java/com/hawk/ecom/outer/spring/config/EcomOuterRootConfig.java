package com.hawk.ecom.outer.spring.config;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.hawk.ecom.outer.service.ChargeDataConfigure;
import com.hawk.ecom.outer.spring.config.EcomOuterRootConfig.WebPackage;


@Configuration
@PropertySource("classpath:/com/hawk/ecom/outer/env/chargeDataConfigure.properties")
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.outer" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class EcomOuterRootConfig {
	
	@Autowired
	private Environment env;

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.outer\\.controller"));
		}
	}
	
	@Bean
	public ChargeDataConfigure chargeDataConfigure(){
		ChargeDataConfigure chargeDataConfigure = new ChargeDataConfigure();
		chargeDataConfigure.setNotifyUrl(env.getProperty("chargeDataConfigure.notifyUrl"));
		return chargeDataConfigure;
	}
}
