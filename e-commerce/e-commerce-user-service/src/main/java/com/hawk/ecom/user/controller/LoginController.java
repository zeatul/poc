package com.hawk.ecom.user.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.user.constant.ConstLoginStatus;
import com.hawk.ecom.user.constant.ConstRegisterChannel;
import com.hawk.ecom.user.exception.TokenEmptyRuntimeException;
import com.hawk.ecom.user.request.LoginParam;
import com.hawk.ecom.user.response.LoginResponse;
import com.hawk.ecom.user.response.UserInfoResponse;
import com.hawk.ecom.user.service.LoginService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.StringTools;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login", method = POST)
	public WebResponse<LoginResponse> login(HttpServletRequest request) throws Exception {
		LoginParam loginParam = HttpRequestTools.parse(request, LoginParam.class);
		loginParam.setLoginIp(AuthThreadLocal.getHttpRequestInfo().getIp());
		loginParam.setUserAgent(AuthThreadLocal.getHttpRequestInfo().getUserAgent());
		loginParam.setLoginChannel(ConstRegisterChannel.HTML5);
		String token = loginService.login(loginParam);
		LoginResponse loginResponse = new LoginResponse();

		loginResponse.setToken(token);

		return SuccessResponse.build(loginResponse);
	}
	
	@RequestMapping(value = "/logout", method = POST)
	public WebResponse<LoginResponse> logout(HttpServletRequest request) throws Exception {
		String token = AuthThreadLocal.getToken();
		loginService.logout(token);

		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/login/info", method = {POST,GET})
	public WebResponse<UserInfoResponse> loginInfo(HttpServletRequest request) throws Exception {
		String token = AuthThreadLocal.getToken();
		if (StringTools.isNullOrEmpty(token)){
			throw new TokenEmptyRuntimeException();
		}
		UserInfoResponse userInfoResponse = loginService.loginInfo(token);

		return SuccessResponse.build(userInfoResponse);
	}
}
