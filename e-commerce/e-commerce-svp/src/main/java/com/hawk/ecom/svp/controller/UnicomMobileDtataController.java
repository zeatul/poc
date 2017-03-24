package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.framework.utility.tools.StringTools;


/**
 * 联通公司的接口
 * @author pzhang1
 *
 */
@RestController
@RequestMapping("/unicom")
public class UnicomMobileDtataController {
	
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to unicom family!!!";
	}

	@RequestMapping(value="/valid/order/number/{orderNumber}",method = GET)
	public String validOrderNumber(@PathVariable String orderNumber) {
		if (StringTools.isNullOrEmpty(orderNumber))
			return "false";
		return "success to receive orderNumber = " + orderNumber;
	}
}
