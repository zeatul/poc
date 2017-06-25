package com.hawk.ecom.query.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.ecom.query.spring.config.EcomQueryRootConfig.WebPackage;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.query" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class EcomQueryRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.query\\.controller"));
		}
	}
}
