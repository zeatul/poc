package com.hawk.ecom.trans.request;

import java.util.List;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.ValidCollection;

public class CreateOrderParam {
	
	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getOrderCustomerMemo() {
		return orderCustomerMemo;
	}

	public void setOrderCustomerMemo(String orderCustomerMemo) {
		this.orderCustomerMemo = orderCustomerMemo;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public List<OrderDetailParam> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailParam> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@ValidCollection("订单明细集合")
	@NotEmpty("订单明细集合")
	private List<OrderDetailParam> orderDetails;
	
	/**
	 * 订单类型 order_type
	 */
	@NotNull
	private Integer orderType;
	
	/**
	 * 订单描述
	 */
	private String orderDesc;
	
	/**
	 * 订单客户备注 order_customer_memo
	 */
	private String orderCustomerMemo;
	
	
	/**
	 * 支付方式 pay_type
	 */
	@NotNull
	private Integer payType;
	
	@NotLogin
	private String userCode;
	

}
