package com.hawk.ecom.task.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/ecom/task/notifiy/chargeData")
@CrossOrigin
public class HomeController {
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/task/chargeData controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
}