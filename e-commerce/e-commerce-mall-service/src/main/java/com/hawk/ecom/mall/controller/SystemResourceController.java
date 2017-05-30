package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.persist.domain.SystemResourceDomain;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.mall.response.SystemResourceInfoResponse;
import com.hawk.ecom.mall.service.SystemResourceService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/resource")
@CrossOrigin
public class SystemResourceController {
	
	@Autowired
	private SystemResourceService  systemResourceService;


	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to mall resource controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<SystemResourceInfoResponse> createResource(HttpServletRequest request) throws Exception {
		SystemCreateResourceParam param = HttpRequestTools.parse(request, SystemCreateResourceParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		SystemResourceDomain systemResourceDomain =  systemResourceService.createResource(param);
		SystemResourceInfoResponse result = DomainTools.copy(systemResourceDomain, SystemResourceInfoResponse.class);
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<MallUserInfoResponse> removeResource(HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MallUserInfoResponse> listResource(HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<MallUserInfoResponse> updateResource(HttpServletRequest request) throws Exception {
		return null;
	}
	
	@RequestMapping(value = "/code/{code}", method = GET)
	public WebResponse<MallUserInfoResponse> loadResource(HttpServletRequest request) throws Exception {
		return null;
	}
}
