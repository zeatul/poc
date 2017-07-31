package com.hawk.ecom.pay.service;

public class AlipayConfig {

	// 商户appid
	public static String APPID = "2017072507887549";

	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfPSrtdjOWDMbkSYPw3CNZPG+dGygLN3yAkBugpIQBH7frzl2HOPlDfYdf93Ub+UWOBjyP4sbc05RHe43OzUsoMKAyDOFWjxCTKjVSKekuj05eJbVtYUuLNjiNhVvmgZcXD/oMF7uUzohBc50tjQSsshOSo3tf3GZaOwcDXcBrFeGReSmE9YCLNCdKOxpZRGFuB5/mpAx3vFkx2IX/mgMuQbGPrMYmzaRONq0w/W2oOV0a6NtcL/JgHCnMNHdtRA5DSZBYGSlI0dTV1ihukiSR5den6d2IGVLdq7gxIpP9qKOtm+yCM9oUDdv1VcKNyBG++cj1v5bZT7+/ka2qMPF/AgMBAAECggEAVRSRgx7MoTdibUb6QA7etCceW4rO8m9LDkXw/Pvpq9AOrOcuAbnnNLA1TIkjATdGeoPKh/Za2lIRv7+8HOqDZY3Viehn3Z/C815x7tyFwmK/NH5hWXfKrS7jOHowiBBlceUPbLahevh+xLnJGTVncf4Thx3iJW3VIhC3xoyo6o2CnpQAn+ZISu9bzy+AVqD+27K9OL7wBkJOHF/iqEyfy4fJc0eLxt+/whJ+zCl9Jq6H5ZYd3qTIJ/d+Q6aGAJWPXY4ja1/HUUHtpyelOCkygsSirqJRnRxZdAfPHq36+iyq596nhD9ZkFLErxS+nn0GLGCFAJiNy1GA6Cxu9UhMQQKBgQD0tR2yIMBe74Wdm8H4jZSFcDbLkd3hpiT9roYu6y7doj4PtPqtWE8+yAf82PBmcS1iXIPsNWiz06y8OMt4zZIMW2wvdRTbnnxMI5GcbK8PvYlTawHkLwsk3yJ7OP/Nph5HYg3PNVCVwz/CWDRORs827fMm+BPjJS07N5lHeL92BQKBgQCmlluW1EcV5tdBG/AKag+9IlvY/BOZYXQds4ewGLGBqeeFlRaV3VLkT9S6h9VHqsRIvGuTlWrXwFD4DSVt7qCjExr1byEtMnvo5oRTKK0E8067bR8vOBVTXXPADQl+/GtiK1Esx0CDEz6X7vG+xEjF7RwHdWmfcEtennLv3Dt8swKBgF2lUg/w6K1sDD4cdUahJ81I/C87SyYnZtCOEVuzNOQsSoPIRoY/3VOxSR1CB/2sIioaLYIfjkoDH3+IXJWGVfXUproinDDPndGlavvpu7TSW9NIiBjCyAj8Oz3cukgA4mV+6Ln7NTOa3LOK/blW78Wdmge3uCSc/YpD5KytUNfhAoGAIDKxky6GatJKSphWxfyH/Dt22zKlqyCt68u2by/mpzQqvqP5UJANpHlVmZQFgiuccp6QgF8B3p+aRsdrs5rKy5o2vLd1ByJZ1sMvUk9rWaiZUBqgS2UgohNO+xDKBBRPwRK0Rt5gNrVyhLU3XnLbyf2xTDPCbYGr36bqejfr+4cCgYEAgGuzHt19IHarbku6GPWlMDq1lL+CJXZT3uiRDWTMm1+G124PmGseOM6llB4RoQzjISh3cVhohcvuMmkectsOhzI9InlJ2S6IoWG1Me8TDelbLErcYqA3eJoYinp5w/SjmtklCCiMCNZvn8sfCXtoSaeV1ByrwJLexSzxBQUa9jI=";

	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String NOTIFY_URL = null;//"https://vstst.fexie.com.cn/ecom/pay/notify/alipay";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// 商户可以自定义同步跳转地址
	public static String RETURN_URL = null;//"https://vstst.fexie.com.cn/ecom/pay/wap/return/alipay";
	// 请求网关地址
	public static String TRADE_URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";

	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAi6e8npLD7h/6nYWguZA9hCBO5Dz6lbofzHqoBrx+nw2l8zGVEQPPCFD1EBWF1poajt1aeIL/cJR839FXBmE6edwBrZvirY/nmiWqcsKwYGsyqZMi5/h2dSMI8k1BGTEVr+Uq0JOTADqqRgp71Q41r/xCKo2qqIj8SwAfjxo/xplkCk20bNkeSDI2N83IG9YT0dyeJSoLkT2aKcPxJ78gFTWHt2+bQq21vO9Uuq47r+07pJnZ054ZhrNvm3XLOPQS7Aou8ALVNjMJHWBql8pro1FfjHZARIIpvBvnMtSq/Vwz2YNbKm0BObqrEqbkAKDqFqGGUJaaMEO8I2IYEaFZJQIDAQAB";

	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";

}
