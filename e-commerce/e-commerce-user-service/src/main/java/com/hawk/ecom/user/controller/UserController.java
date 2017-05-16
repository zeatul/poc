package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.sms.service.SmsService;
import com.hawk.ecom.user.constant.ConstRegisterChannel;
import com.hawk.ecom.user.exception.UnMatchedVeriCodeRuntimeException;
import com.hawk.ecom.user.exception.UserNotFoundRuntimeException;
import com.hawk.ecom.user.persist.domain.UserDomain;
import com.hawk.ecom.user.request.CreateUserParam;
import com.hawk.ecom.user.request.RegisterUserParam;
import com.hawk.ecom.user.request.ResetPasswordParam;
import com.hawk.ecom.user.request.SsoParam;
import com.hawk.ecom.user.request.UpdatePasswordParam;
import com.hawk.ecom.user.request.UpdateUserInfoParam;
import com.hawk.ecom.user.response.LoginResponse;
import com.hawk.ecom.user.response.SsoResponse;
import com.hawk.ecom.user.response.UserInfoResponse;
import com.hawk.ecom.user.service.LoginService;
import com.hawk.ecom.user.service.UserService;
import com.hawk.ecom.user.utils.SsoToolsForSignature;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

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
		return "Welcome to user controller!!!" +", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@Autowired
	private SmsService smsService;
	
	public static void main(String[] args){
		String token = "111111111111";
		SsoResponse ssoResponse = new SsoResponse();
		ssoResponse.setToken(token);
		System.out.println(JsonTools.toJsonString(SuccessResponse.build(ssoResponse)));
	}
	
	@RequestMapping(value = "/sso", method = {POST,GET})
	public WebResponse<SsoResponse> sso(HttpServletRequest request) throws Exception{
		SsoParam ssoParam = SsoToolsForSignature.parse(request, SsoParam.class);
		ssoParam.setRegisterIp(AuthThreadLocal.getHttpRequestInfo().getIp());
		ssoParam.setUserAgent(AuthThreadLocal.getHttpRequestInfo().getUserAgent());
		String token = userService.sso(ssoParam);
		SsoResponse ssoResponse = new SsoResponse();
		ssoResponse.setToken(token);
		return  SuccessResponse.build(ssoResponse);
	}
	
	@RequestMapping(value = "/register", method = POST)
	public WebResponse<LoginResponse> register(HttpServletRequest request) throws Exception{
		RegisterUserParam registerUserParam = HttpRequestTools.parse(request, RegisterUserParam.class);
		String veriCode = registerUserParam.getVeriCode();		
		String mobileNumber = registerUserParam.getMobileNumber();
		String loginPwd = registerUserParam.getLoginPwd();
		String registerChannel = registerUserParam.getRegisterChanel();
		if (StringTools.isNullOrEmpty(registerChannel)){
			registerChannel = ConstRegisterChannel.HTML5;
		}
		
		if (!smsService.checkVeriCode(mobileNumber, veriCode)){
			throw new UnMatchedVeriCodeRuntimeException();
		}
		
		CreateUserParam createUserParam = new CreateUserParam();
		
		createUserParam.setLoginPwd(loginPwd);
		createUserParam.setMobileNumber(mobileNumber);
		createUserParam.setRegisterIp(AuthThreadLocal.getHttpRequestInfo().getIp());
		createUserParam.setUserAgent(AuthThreadLocal.getHttpRequestInfo().getUserAgent());
		createUserParam.setRegisterChannel(registerChannel);
		
		userService.createUser(createUserParam);
		
		String  token = loginService.login(mobileNumber, loginPwd,createUserParam.getRegisterIp(),createUserParam.getUserAgent(),registerChannel);
		
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
	
	@RequestMapping(value = "/info/update", method = POST)
	public WebResponse<ResponseData> updateUserInfo(HttpServletRequest request) throws Exception{
		UpdateUserInfoParam updateUserInfoParam = HttpRequestTools.parse(request, UpdateUserInfoParam.class);
		updateUserInfoParam.setUserId(AuthThreadLocal.getUserId()); 
		userService.updateUserInfo(updateUserInfoParam);		
		return SuccessResponse.build();
	}
	
	@RequestMapping(value = "/info", method = {POST,GET})
	public WebResponse<ResponseData> userInfo(HttpServletRequest request) throws Exception{
		UserDomain userDomain = userService.loadById(AuthThreadLocal.getUserId());
		if (userDomain == null)
			throw new UserNotFoundRuntimeException();
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setMobileNumber(userDomain.getMobileNumber());
		userInfoResponse.setNickname(userDomain.getUserNickname());
		userInfoResponse.setBirthday(userDomain.getUserBirthday());
		userInfoResponse.setSex(userDomain.getUserSex());
		return SuccessResponse.build(userInfoResponse);
	}
}
