package com.hawk.ecom.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wxpay.sdk.WXPay;

@Service
public class WXPayService {
	
	@Autowired
	private WXPay wxpay;

}
