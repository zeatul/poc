package com.hawk.ecom.muser.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.ecom.muser.spring.config.MuserRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.muser" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class MuserRootConfig {

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.muser\\.controller"));
		}
	}
	
	@Bean("mallUserCodeSequenceService")
	public PkGenService mallUserCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(100000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_msr_mall_user_code_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	
}
