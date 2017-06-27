package com.hawk.ecom.trans.service;

import com.hawk.ecom.trans.persist.domain.OrderDomain;
import com.hawk.ecom.trans.request.CreateOrderParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;

public class OrderService {
	
	@Valid
	public OrderDomain createOrder(@Valid @NotNull("函数入参") CreateOrderParam createOrderParam){
		createOrderParam.getOrderDetails();
	}

}
