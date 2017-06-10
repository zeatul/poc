package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.constant.ConstLoginChannel;
import com.hawk.ecom.mall.exception.MallTokenEmptyRuntimeException;
import com.hawk.ecom.mall.persist.domain.MallUserDomain;
import com.hawk.ecom.mall.request.MallCreateUserParam;
import com.hawk.ecom.mall.request.MallListUserParam;
import com.hawk.ecom.mall.request.MallLoginParam;
import com.hawk.ecom.mall.request.MallResetPasswordParam;
import com.hawk.ecom.mall.request.MallUpdatePasswordParam;
import com.hawk.ecom.mall.request.MallUpdateUserParam;
import com.hawk.ecom.mall.request.MallUpdateUserStatusParam;
import com.hawk.ecom.mall.response.MallLoginResponse;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.mall.response.MallMultiUserInfoResponse;
import com.hawk.ecom.mall.service.MallUserService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.StringTools;

@RestController
@RequestMapping("/mall/admin/user")
@CrossOrigin
public class MallUserController {

	@Autowired
	private MallUserService mallUserService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to mall user controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/logout", method = POST)
	public WebResponse<ResponseData> logout(HttpServletRequest request) throws Exception {
		String token = AuthThreadLocal.getToken();
		mallUserService.logout(token);
		return SuccessResponse.build(null);
	}

	@RequestMapping(value = "/login", method = POST)
	public WebResponse<ResponseData> login(HttpServletRequest request) throws Exception {
		MallLoginParam mallLoginParam = HttpRequestTools.parse(request, MallLoginParam.class);
		mallLoginParam.setLoginIp(AuthThreadLocal.getHttpRequestInfo().getIp());
		mallLoginParam.setUserAgent(AuthThreadLocal.getHttpRequestInfo().getUserAgent());
		mallLoginParam.setLoginChannel(ConstLoginChannel.HTML5);
		String token = mallUserService.login(mallLoginParam);
		MallLoginResponse mallLoginResponse = new MallLoginResponse();
		mallLoginResponse.setToken(token);
		return SuccessResponse.build(mallLoginResponse);
	}

	@RequestMapping(value = "/login/info", method = { POST, GET })
	public WebResponse<MallUserInfoResponse> loginInfo(HttpServletRequest request) throws Exception {
		String token = AuthThreadLocal.getToken();
		if (StringTools.isNullOrEmpty(token)) {
			throw new MallTokenEmptyRuntimeException();
		}
		MallUserDomain mallUserDomain = mallUserService.loginInfo(token);

		return SuccessResponse.build(DomainTools.copy(mallUserDomain, MallUserInfoResponse.class));
	}

	@RequestMapping(value = "/pwd/reset", method = POST)
	public WebResponse<ResponseData> resetPassword(HttpServletRequest request) throws Exception {
		MallResetPasswordParam resetPasswordParam = HttpRequestTools.parse(request, MallResetPasswordParam.class);
		mallUserService.resetPassword(resetPasswordParam);
		return SuccessResponse.build();

	}

	@RequestMapping(value = "/pwd/update", method = POST)
	public WebResponse<ResponseData> updatePassword(HttpServletRequest request) throws Exception {
		MallUpdatePasswordParam updatePasswordParam = HttpRequestTools.parse(request, MallUpdatePasswordParam.class);
		mallUserService.updatePassword(updatePasswordParam);
		return SuccessResponse.build();
	}

	@RequestMapping(value = "/create", method = POST)
	public WebResponse<MallUserInfoResponse> createUser(HttpServletRequest request) throws Exception {
		MallCreateUserParam mallCreateUserParam = HttpRequestTools.parse(request, MallCreateUserParam.class);
		mallCreateUserParam.setOperatorCode(AuthThreadLocal.getUserCode());
		
		MallUserDomain mallUserDomain = mallUserService.createUser(mallCreateUserParam);
		MallUserInfoResponse mallUserInfoResponse = DomainTools.copy(mallUserDomain, MallUserInfoResponse.class);
		return SuccessResponse.build(mallUserInfoResponse);
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<MallUserInfoResponse> updateUser(HttpServletRequest request) throws Exception {
		MallUpdateUserParam param = HttpRequestTools.parse(request, MallUpdateUserParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		
		mallUserService.updateUser(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/status/update", method = POST)
	public WebResponse<ResponseData> updateUserStatus(HttpServletRequest request) throws Exception {
		MallUpdateUserStatusParam param = HttpRequestTools.parse(request, MallUpdateUserStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		mallUserService.updateUserStatus(param);
		return SuccessResponse.build(null);
	} 

	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MallMultiUserInfoResponse> listUser(HttpServletRequest request) throws Exception {
		MallListUserParam param = HttpRequestTools.parse(request, MallListUserParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		
		List<MallUserDomain> mallUserDomainList = mallUserService.listMallUser(param);
		MallMultiUserInfoResponse result = new MallMultiUserInfoResponse(DomainTools.copy(mallUserDomainList, MallUserInfoResponse.class));
		return SuccessResponse.build(result);
	}
}
