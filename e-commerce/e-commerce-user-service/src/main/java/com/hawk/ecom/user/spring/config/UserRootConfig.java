package com.hawk.ecom.user.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.ecom.user.spring.config.UserRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.user" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class UserRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.user\\.controller"));
		}
	}
	
	@Bean("userCodeSequenceService")
	public PkGenService userCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(10000000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_usr_user_code_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
}
