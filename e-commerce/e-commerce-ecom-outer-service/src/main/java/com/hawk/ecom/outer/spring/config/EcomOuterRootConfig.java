package com.hawk.ecom.outer.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import com.hawk.ecom.outer.spring.config.EcomOuterRootConfig.WebPackage;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.outer" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class EcomOuterRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.outer\\.controller"));
		}
	}
}
