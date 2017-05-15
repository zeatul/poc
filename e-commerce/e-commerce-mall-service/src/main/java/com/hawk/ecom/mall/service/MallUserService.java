package com.hawk.ecom.mall.service;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mall/user")
@CrossOrigin
public class MallUserService {
	
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to mall!!!";
	}
}
