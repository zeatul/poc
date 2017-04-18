package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.service.UserService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to user!!!";
	}
	
	
	@RequestMapping(value = "/register", method = POST)
	public WebResponse<ResponseData> register(HttpServletRequest request) throws Exception{
		RegisterUserParam registerUserParam = HttpRequestTools.parse(request, RegisterUserParam.class);
		
		CreateUserParam createUserParam = new CreateUserParam();
		createUserParam.setLoginPwd(registerUserParam.getLoginPwd());
		createUserParam.setMobileNumber(registerUserParam.getMobileNumber());
		
		userService.createUser(createUserParam);
		return SuccessResponse.build(null);
	}
}
