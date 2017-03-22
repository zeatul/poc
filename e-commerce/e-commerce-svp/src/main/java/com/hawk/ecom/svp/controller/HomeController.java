package com.hawk.ecom.svp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
	
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/home", method = GET)
	@ResponseBody
	public Map<String,String> home() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "hello");
		map.put("2", "hello2");
		System.out.println("logback is goint to work");
		logger.error("logback is working");
		return map;
	}
	
	@RequestMapping(value = "/error", method = GET)
	public String error() throws Exception {
		throw new Exception("Not Found");
	}
}
