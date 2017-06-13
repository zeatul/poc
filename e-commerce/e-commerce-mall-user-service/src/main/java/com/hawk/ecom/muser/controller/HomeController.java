package com.hawk.ecom.muser.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/mall/admin/home")
@CrossOrigin
public class HomeController {
	
	@Autowired
	private CacheService cacheService;
	
	@RequestMapping(value = "/cache/get", method = GET)
	public String home() {
		
		return cacheService.get("kkkk", String.class);
	}
	
	@RequestMapping(value = "/cache/put", method = GET)
	public String put() {
		cacheService.put("kkkk", "cached flag "+DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN));
		
		return "Success to put cache!";
	}


}
