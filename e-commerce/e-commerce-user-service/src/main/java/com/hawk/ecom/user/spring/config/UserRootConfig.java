package com.hawk.ecom.user.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.hawk.ecom.user.spring.config.UserRootConfig.WebPackage;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.user" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class UserRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.user\\.controller"));
		}
	}
}
