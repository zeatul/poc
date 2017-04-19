package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.user.request.LoginParam;
import com.hawk.ecom.user.request.SendVeriCodeParam;
import com.hawk.ecom.user.response.LoginResponse;
import com.hawk.ecom.user.service.LoginService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = POST)
	public WebResponse<LoginResponse> login(HttpServletRequest request) throws Exception {
		LoginParam loginParam = HttpRequestTools.parse(request, LoginParam.class);
		String token = loginService.login(loginParam);
		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setToken(token);

		return SuccessResponse.build(loginResponse);
	}
}
