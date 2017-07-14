package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.query.persist.domainex.SystemResourceExDomain;
import com.hawk.ecom.query.service.SystemResourceService;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/ecom/query/system/resource")
@CrossOrigin
public class SystemResourceController {
	
	@Autowired
	private SystemResourceService systemResourceService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/query/system/resource controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/h5main", method = {POST,GET})
	public WebResponse<MultiResponse<SystemResourceExDomain>> h5main(HttpServletRequest request) throws Exception {
		
		List<SystemResourceExDomain> list = systemResourceService.h5main();
		return SuccessResponse.build(new MultiResponse<SystemResourceExDomain>(list));
	}
}
