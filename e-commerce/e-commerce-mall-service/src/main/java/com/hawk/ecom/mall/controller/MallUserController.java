package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;

@RestController
@RequestMapping("/mall/user")
@CrossOrigin
public class MallUserController {
	
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to mall user controller!!!" +", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/logout", method = POST)
	public WebResponse<ResponseData> logout(HttpServletRequest request) throws Exception{
		return null;
		
	}  
	
	@RequestMapping(value = "/login", method = POST)
	public WebResponse<ResponseData> login(HttpServletRequest request) throws Exception{
		
		return null;
	}  
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<ResponseData> createUser(HttpServletRequest request) throws Exception{
		
		return null;
	} 
}
