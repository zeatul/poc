package com.hawk.ecom.pay.service;

public class AlipayConfig {
	
	//hello
	
	public final static String version = "111111";

	// 商户appid
	public static String APPID = "2088421800331408";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKsiQTl8Vdw0iRNfjMMuhpgIK8cwzhHXnWt3SQMQ520OijY60M6JIaPq/wLCSaHLFTQFg95xd25kvBRQRttLKENTfFkd2Oh1qG1PsUXfzsqBsm5vVxZMhLs/ElMNaIusTrQoAmSKHkO47m3yhSX8mndKkZuybSQOf7vtY+tSkMb1AgMBAAECgYAkOdcWxSQjvhc6O6K/emzRaAFkZAdrLXpMUm7pMD3dDxPg0Oh8n/i4Czm6KsiNcEDnxzcX8mr2umo53phxOWKnLR+GuZ539XSdlwjFadQ+NYJxQE+g5YF+rDp7q1MBFMzlOSqJotXLT3TqHKBXjHxsfgb6r2m2AYEqr5giYfVtTQJBANvGZ4Wt4a98aTdsXCtsG5Tfum1YWblC0RJWDVVM/7PmVMtOUD+6EhEPQwUCOi9aYRlYDKMexMN6G2T9csNHU5MCQQDHV2SN+nc11u2IedQC2r0QkrsLAJ1xPb21NjZepyfPeqi7BY2rE+bcVG08/8DPJsmyIStLh5/f/aIkhFQYOyBXAkAfNC9UmpiViuuBdAAuJh5QZ+eXSNkMZcE7gj5IYzNW/pgBebAJLEkOZQVV+7f5KzLoH0lEC8VSH88t6pJVyZXDAkB1Q+LiZrokEPiQ9WIT/8uC6V5g3Y7A5BoqdObNDbYsprKFuoGZZ07CxGOyo50v/1hdkoeemf0hzlnI85No4bdnAkEAhUHnhFAuhmMiMYaVvG6OPggdZOssWr5WfRHXcMxJesjxuX/qVZPEm3rq4ndwqujKk1l/zELaXvpUQ+kHr8TnhQ==";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String NOTIFY_URL = "/ecom/pay/notify/alipay";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// 商户可以自定义同步跳转地址
	public static String RETURN_URL = "/ecom/pay/wap/return/alipay";
	// 请求网关地址
	public static String TRADE_URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKsiQTl8Vdw0iRNfjMMuhpgIK8cwzhHXnWt3SQMQ520OijY60M6JIaPq/wLCSaHLFTQFg95xd25kvBRQRttLKENTfFkd2Oh1qG1PsUXfzsqBsm5vVxZMhLs/ElMNaIusTrQoAmSKHkO47m3yhSX8mndKkZuybSQOf7vtY+tSkMb1AgMBAAECgYAkOdcWxSQjvhc6O6K/emzRaAFkZAdrLXpMUm7pMD3dDxPg0Oh8n/i4Czm6KsiNcEDnxzcX8mr2umo53phxOWKnLR+GuZ539XSdlwjFadQ+NYJxQE+g5YF+rDp7q1MBFMzlOSqJotXLT3TqHKBXjHxsfgb6r2m2AYEqr5giYfVtTQJBANvGZ4Wt4a98aTdsXCtsG5Tfum1YWblC0RJWDVVM/7PmVMtOUD+6EhEPQwUCOi9aYRlYDKMexMN6G2T9csNHU5MCQQDHV2SN+nc11u2IedQC2r0QkrsLAJ1xPb21NjZepyfPeqi7BY2rE+bcVG08/8DPJsmyIStLh5/f/aIkhFQYOyBXAkAfNC9UmpiViuuBdAAuJh5QZ+eXSNkMZcE7gj5IYzNW/pgBebAJLEkOZQVV+7f5KzLoH0lEC8VSH88t6pJVyZXDAkB1Q+LiZrokEPiQ9WIT/8uC6V5g3Y7A5BoqdObNDbYsprKFuoGZZ07CxGOyo50v/1hdkoeemf0hzlnI85No4bdnAkEAhUHnhFAuhmMiMYaVvG6OPggdZOssWr5WfRHXcMxJesjxuX/qVZPEm3rq4ndwqujKk1l/zELaXvpUQ+kHr8TnhQ==";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";

}
