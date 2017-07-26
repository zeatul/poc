package com.hawk.ecom.product.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.product.service" } )
public class ProductRootConfig {

	
	
	@Bean("smallNumberSequenceService")
	public PkGenService mallUserCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(10000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_prd_small_number_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	
}
