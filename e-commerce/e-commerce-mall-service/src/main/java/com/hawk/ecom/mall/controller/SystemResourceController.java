package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/mall/admin/resource")
@CrossOrigin
public class SystemResourceController {


	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to mall resource controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<MallUserInfoResponse> createResource(HttpServletRequest request) throws Exception {
		
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<MallUserInfoResponse> removeResource(HttpServletRequest request) throws Exception {
		
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MallUserInfoResponse> listResource(HttpServletRequest request) throws Exception {
		
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<MallUserInfoResponse> updateResource(HttpServletRequest request) throws Exception {
		
	}
	
	@RequestMapping(value = "/code/{code}", method = GET)
	public WebResponse<MallUserInfoResponse> loadResource(HttpServletRequest request) throws Exception {
		
	}
}
