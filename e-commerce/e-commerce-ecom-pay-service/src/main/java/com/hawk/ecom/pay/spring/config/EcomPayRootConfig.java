package com.hawk.ecom.pay.spring.config;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfigImpl;
import com.hawk.ecom.pay.service.AlipayConfig;
import com.hawk.ecom.pay.spring.config.EcomPayRootConfig.WebPackage;
import com.hawk.framework.pub.pk.MysqlPkGenerator;
import com.hawk.framework.pub.pk.PkGenService;


@Configuration
@Import({})
@PropertySource("classpath:/com/hawk/ecom/pay/env/pay.properties")
@ComponentScan(basePackages = { "com.hawk.ecom.pay" }, excludeFilters = { @Filter(type = FilterType.CUSTOM, value = WebPackage.class) })
public class EcomPayRootConfig {
	
	@Autowired
	private Environment env;

	public static class WebPackage extends RegexPatternTypeFilter {
		public WebPackage() {
			super(Pattern.compile("com\\.hawk\\.ecom\\.pay\\.controller"));
		}
	}

	@Bean
	public AlipayClient alipayClient() {
		String notifyBaseUrl = env.getProperty("pay.alipay.notify.baseUrl");
		String returnBaseUrl = env.getProperty("pay.alipay.return.baseUrl");
		AlipayConfig.NOTIFY_URL = notifyBaseUrl + AlipayConfig.NOTIFY_URL;
		AlipayConfig.RETURN_URL = returnBaseUrl + AlipayConfig.RETURN_URL;
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
