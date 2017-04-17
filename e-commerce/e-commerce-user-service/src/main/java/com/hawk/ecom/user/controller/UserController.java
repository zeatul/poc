package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to user!!!";
	}
}
