package com.hawk.ecom.pay.request;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class NotifyParam {
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentBillCode() {
		return paymentBillCode;
	}

	public void setPaymentBillCode(String paymentBillCode) {
		this.paymentBillCode = paymentBillCode;
	}

	@NotEmpty
	private String paymentBillCode;
	
	@NotNull
	private BigDecimal totalAmount;

}
