package com.hawk.ecom.bsi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

	@RequestMapping(value = "/home", method = GET)
	@ResponseBody
	public Map<String,String> home() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "hello");
		map.put("2", "hello2");
		return map;
	}
	
	@RequestMapping(value = "/error", method = GET)
	public String error() throws Exception {
		throw new Exception("Not Found");
	}
}
