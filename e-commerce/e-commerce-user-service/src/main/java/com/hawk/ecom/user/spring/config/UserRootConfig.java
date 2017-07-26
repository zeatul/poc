package com.hawk.ecom.user.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.user.service"  })
public class UserRootConfig {

	
	
	@Bean("userCodeSequenceService")
	public PkGenService userCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(100000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_usr_user_code_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
}
