package com.hawk.ecom.mall.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.ecom.mall.spring.config.MallRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.mall" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class MallRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.mall\\.controller"));
		}
	}
	
	@Bean("mallUserCodeSequenceService")
	public PkGenService mallUserCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator();
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_mal_mall_user_code_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
}
