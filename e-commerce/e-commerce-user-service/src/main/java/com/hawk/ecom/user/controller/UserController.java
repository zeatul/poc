package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.sms.service.SmsService;
import com.hawk.ecom.user.constant.ConstRegisterChannel;
import com.hawk.ecom.user.exception.UnMatchedVeriCodeRuntimeException;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.ResetPasswordParam;
import com.hawk.ecom.user.request.UpdatePasswordParam;
import com.hawk.ecom.user.response.LoginResponse;
import com.hawk.ecom.user.service.LoginService;
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
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to user!!!";
	}
	
	@Autowired
	private SmsService smsService;
	
	
	@RequestMapping(value = "/register", method = POST)
	public WebResponse<LoginResponse> register(HttpServletRequest request) throws Exception{
		RegisterUserParam registerUserParam = HttpRequestTools.parse(request, RegisterUserParam.class);
		String veriCode = registerUserParam.getVeriCode();		
		String mobileNumber = registerUserParam.getMobileNumber();
		String loginPwd = registerUserParam.getLoginPwd();
		if (!smsService.checkVeriCode(mobileNumber, veriCode)){
			throw new UnMatchedVeriCodeRuntimeException();
		}
		
		CreateUserParam createUserParam = new CreateUserParam();
		
		createUserParam.setLoginPwd(loginPwd);
		createUserParam.setMobileNumber(mobileNumber);
		createUserParam.setRegisterChannel(ConstRegisterChannel.SELF_REG);
		createUserParam.setRegisterIp(AuthThreadLocal.getHttpRequestInfo().getIp());
		createUserParam.setUserAgent(AuthThreadLocal.getHttpRequestInfo().getUserAgent());
		
		userService.createUser(createUserParam);
		
		String  token = loginService.login(mobileNumber, loginPwd,createUserParam.getRegisterIp(),createUserParam.getUserAgent());
		
		LoginResponse loginResponse = new LoginResponse();
		
		loginResponse.setToken(token);
		
		return SuccessResponse.build(loginResponse);
	}
	
	@RequestMapping(value = "/pwd/reset", method = POST)
	public WebResponse<ResponseData> resetPassword(HttpServletRequest request) throws Exception{
		ResetPasswordParam resetPasswordParam = HttpRequestTools.parse(request, ResetPasswordParam.class);
		userService.resetPassword(resetPasswordParam);
		return SuccessResponse.build();
		
	}
	
	@RequestMapping(value = "/pwd/update", method = POST)
	public WebResponse<ResponseData> updatePassword(HttpServletRequest request) throws Exception{
		UpdatePasswordParam updatePasswordParam = HttpRequestTools.parse(request, UpdatePasswordParam.class);
		userService.updatePassword(updatePasswordParam);
		return SuccessResponse.build();
	}
}
