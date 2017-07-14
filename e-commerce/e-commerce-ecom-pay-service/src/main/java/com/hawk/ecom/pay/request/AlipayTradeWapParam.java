package com.hawk.ecom.pay.request;

import com.hawk.ecom.user.annotation.NotLogin;
import com.hawk.framework.dic.validation.annotation.NotNull;

public class AlipayTradeWapParam {
	
	@NotNull
	private Integer orderId;
	
	@NotLogin
	private String userCode;
	 
	

}
