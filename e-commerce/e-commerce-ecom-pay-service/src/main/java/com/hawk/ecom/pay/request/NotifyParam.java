package com.hawk.ecom.pay.request;

import org.springframework.beans.factory.annotation.Autowired;

public class NotifyParam {
	
	public String getPaymentBillCode() {
		return paymentBillCode;
	}

	public void setPaymentBillCode(String paymentBillCode) {
		this.paymentBillCode = paymentBillCode;
	}

	@Autowired
	private String paymentBillCode;

}
