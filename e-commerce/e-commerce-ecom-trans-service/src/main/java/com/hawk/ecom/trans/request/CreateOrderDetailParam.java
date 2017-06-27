package com.hawk.ecom.trans.request;

import com.hawk.framework.dic.validation.annotation.Min;
import com.hawk.framework.dic.validation.annotation.NotNull;


public class CreateOrderDetailParam {

	@NotNull
	private Integer skuId;
	
	@NotNull
	@Min(minValue="0")
	private Integer skuQuantity;
}
