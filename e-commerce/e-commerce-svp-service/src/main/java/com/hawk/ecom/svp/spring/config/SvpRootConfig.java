package com.hawk.ecom.svp.spring.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hawk.ecom.svp.spring.config.SvpRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


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
	
	
	@Bean("cashCouponCodeSequenceService")
	public PkGenService cashCouponCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(1000000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_svp_bsi_cash_coupon_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	@Bean("orderCodeSequenceService")
	public PkGenService orderCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(1000000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_svp_order_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	@Bean("chargeCodeSequenceService")
	public PkGenService chargeCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(1000000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_svp_charge_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	@Bean("bsiTaskCodeSequenceService")
	public PkGenService bsiTaskCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(1000000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_svp_bsi_out_order_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
	
	
}
