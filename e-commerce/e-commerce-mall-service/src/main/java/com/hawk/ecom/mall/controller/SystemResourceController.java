package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.mall.persist.domain.SystemResourceDomain;
import com.hawk.ecom.mall.request.SystemCreateResourceParam;
import com.hawk.ecom.mall.request.SystemExchangeResourceOrderParam;
import com.hawk.ecom.mall.request.SystemListResourceParam;
import com.hawk.ecom.mall.request.SystemLoadResourceParam;
import com.hawk.ecom.mall.response.MallUserInfoResponse;
import com.hawk.ecom.mall.response.MultiSystemResourceInfoResponse;
import com.hawk.ecom.mall.response.SystemResourceInfoResponse;
import com.hawk.ecom.mall.service.SystemResourceService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.ecom.mall.request.SystemRemoveResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceParam;
import com.hawk.ecom.mall.request.SystemUpdateResourceStatusParam;

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
	public WebResponse<ResponseData> removeResource(HttpServletRequest request) throws Exception {
		SystemRemoveResourceParam param = HttpRequestTools.parse(request, SystemRemoveResourceParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		systemResourceService.removeResource(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiSystemResourceInfoResponse> listResource(HttpServletRequest request) throws Exception {
		SystemListResourceParam param = HttpRequestTools.parse(request, SystemListResourceParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<SystemResourceDomain> systemResourceDomainList = systemResourceService.listResource(param);
		MultiSystemResourceInfoResponse result = new MultiSystemResourceInfoResponse(DomainTools.copy(systemResourceDomainList, SystemResourceInfoResponse.class));
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<MallUserInfoResponse> updateResource(HttpServletRequest request) throws Exception {
		SystemUpdateResourceParam param = HttpRequestTools.parse(request, SystemUpdateResourceParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		systemResourceService.updateResource(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/nodeCode/{nodeCode}", method = {GET,POST})
	public WebResponse<SystemResourceInfoResponse> loadResource(@PathVariable String nodeCode) throws Exception {
		SystemLoadResourceParam param = new SystemLoadResourceParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		
		param.setNodeCode(nodeCode);
		SystemResourceDomain systemResourceDomain = systemResourceService.loadSystemResource(param);
		SystemResourceInfoResponse result = DomainTools.copy(systemResourceDomain, SystemResourceInfoResponse.class);
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/status/update", method = POST)
	public WebResponse<ResponseData> updateResourceStatus(HttpServletRequest request) throws Exception {
		SystemUpdateResourceStatusParam param = HttpRequestTools.parse(request, SystemUpdateResourceStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		systemResourceService.updateResourceStatus(param);
		return SuccessResponse.build(null);
	} 
	
	@RequestMapping(value = "/order/exchange", method = POST)
	public WebResponse<ResponseData> exchangeResourceOrder(HttpServletRequest request) throws Exception {
		SystemExchangeResourceOrderParam param = HttpRequestTools.parse(request, SystemExchangeResourceOrderParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		systemResourceService.exchangeResourceOrder(param);
		return SuccessResponse.build(null);
	}
}
