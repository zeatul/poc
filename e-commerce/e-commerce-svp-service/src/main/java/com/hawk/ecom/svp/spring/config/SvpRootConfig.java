package com.hawk.ecom.svp.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hawk.ecom.svp.spring.config.SvpRootConfig.WebPackage;


@Configuration
@Import({SvpJobConfig.class,SvpOutCallBeanConfig.class})
@ComponentScan(basePackages = { "com.hawk.ecom.svp"}, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
@EnableScheduling
public class SvpRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.svp\\.controller"));
		}
	}
}
