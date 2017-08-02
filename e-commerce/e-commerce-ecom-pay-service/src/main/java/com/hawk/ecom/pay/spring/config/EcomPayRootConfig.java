package com.hawk.ecom.pay.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.hawk.ecom.pay.service.AlipayConfig;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@PropertySource("classpath:/com/hawk/ecom/pay/env/pay.properties")
@ComponentScan(basePackages = { "com.hawk.ecom.pay.service" })
public class EcomPayRootConfig {
	
	@Autowired
	private Environment env;

	@Bean
	public AlipayClient alipayClient() {
		String notifyUrl = env.getProperty("pay.alipay.notify.url").trim();
		String returnUrl = env.getProperty("pay.alipay.return.url").trim();
		AlipayConfig.NOTIFY_URL = notifyUrl;
		AlipayConfig.RETURN_URL = returnUrl;
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.TRADE_URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT,
				AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
		return client;
	}
	
//	@Bean
//	public WXPay wxPay() throws Exception{
//		String certPath = null;
//		WXPayConfigImpl config = new WXPayConfigImpl(certPath);
//	    return new WXPay(config);
//	}
	
	@Bean("paymentBillCodeSequenceService")
	public PkGenService orderCodeSequenceService(JdbcTemplate jdbcTemplate){
		MysqlPkGenerator mysqlPkGenerator = new MysqlPkGenerator(10000);
		mysqlPkGenerator.setJdbcTemplate(jdbcTemplate);
		mysqlPkGenerator.setSql( "replace into t_pay_payment_sequence(stub) values('a')");
		return mysqlPkGenerator;
	}
}
