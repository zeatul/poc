package com.hawk.ecom.trans.request;

import java.util.List;

import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.ValidCollection;

public class CreateOrderParam {
	
	public List<OrderDetailParam> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailParam> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@ValidCollection("订单明细集合")
	@NotEmpty("订单明细集合")
	private List<OrderDetailParam> orderDetails;
	

}
