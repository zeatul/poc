package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.response.SingleProductResponse;
import com.hawk.framework.pub.web.WebResponse;

/**
 * 流量下单接口
 * @author pzhang1
 *
 */
@RestController
@RequestMapping("/svp/mobile")
public class MobileDataController {
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to charge mobile data!!!";
	}
	
	@RequestMapping(value = "/signin", method = GET)
	public WebResponse<Object> signIn(HttpServletRequest request){
		return null;
	}
}
