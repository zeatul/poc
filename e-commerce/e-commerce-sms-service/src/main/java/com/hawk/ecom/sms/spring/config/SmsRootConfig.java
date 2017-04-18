package com.hawk.ecom.sms.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.hawk.ecom.sms.spring.config.SmsRootConfig.WebPackage;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.sms" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class SmsRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.sms\\.controller"));
		}
	}
}
