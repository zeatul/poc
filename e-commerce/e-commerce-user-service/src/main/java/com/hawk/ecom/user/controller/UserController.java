package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.sms.service.SmsOuterCallService;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.SendVeriCodeParam;
import com.hawk.ecom.user.response.LoginResponse;
import com.hawk.ecom.user.service.LoginService;
import com.hawk.ecom.user.service.UserService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.StringTools;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SmsOuterCallService smsOuterCallService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to user!!!";
	}
	
	private Map<String,String> map = new HashMap<String,String>();
	
	@RequestMapping(value = "/veriCode", method = {POST})
	public WebResponse<ResponseData> veriCode(HttpServletRequest request) throws Exception{
		SendVeriCodeParam sendVeriCodeParam = HttpRequestTools.parse(request, SendVeriCodeParam.class);
		String mobileNumber = sendVeriCodeParam.getMobileNumber();
		String veriCode = StringTools.randomNumberString(4);
		String message = "【飞到家权益平台】尊敬的用户，您的动态验证码为:"+veriCode+"。该验证码有效期为30分钟。";
		map.put(mobileNumber, veriCode);
		smsOuterCallService.send(mobileNumber, message, new Long(System.currentTimeMillis()).toString());
		return SuccessResponse.build(null);
	}
	
	
	@RequestMapping(value = "/register", method = POST)
	public WebResponse<LoginResponse> register(HttpServletRequest request) throws Exception{
		RegisterUserParam registerUserParam = HttpRequestTools.parse(request, RegisterUserParam.class);
		String veriCode = registerUserParam.getVeriCode();		
		String mobileNumber = registerUserParam.getMobileNumber();
		String loginPwd = registerUserParam.getLoginPwd();
		if (!veriCode.equals(map.get(mobileNumber))){
			throw new RuntimeException("验证码错误");
		}
		
		CreateUserParam createUserParam = new CreateUserParam();
		
		createUserParam.setLoginPwd(loginPwd);
		createUserParam.setMobileNumber(mobileNumber);
		
		userService.createUser(createUserParam);
		
		String  token = loginService.login(mobileNumber, loginPwd);
		
		LoginResponse loginResponse = new LoginResponse();
		
		loginResponse.setToken(token);
		
		return SuccessResponse.build(loginResponse);
	}
}
