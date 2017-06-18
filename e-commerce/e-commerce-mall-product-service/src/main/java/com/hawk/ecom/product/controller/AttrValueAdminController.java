package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.response.AttrValueInfoResponse;
import com.hawk.ecom.product.service.AttrValueService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/attr/value")
@CrossOrigin
public class AttrValueAdminController {
	
	@Autowired
	private AttrValueService attrValueService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/attr/value controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<AttrValueInfoResponse> createAttrValue(HttpServletRequest request) throws Exception {
		CreateAttrValueParam param = HttpRequestTools.parse(request, CreateAttrValueParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrValueDomain attrValueDomain =  attrValueService.createAttrValue(param);		
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<AttrValueInfoResponse>> listAttrValue(HttpServletRequest request) throws Exception {
		ListAttrValueParam param = HttpRequestTools.parse(request, ListAttrValueParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrValueDomain attrValueDomain =  attrValueService.listAttrValue(param);		
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}
	
	@RequestMapping(value = "/load/id/{id}", method = POST)
	public WebResponse<AttrValueInfoResponse> loadAttrValue(HttpServletRequest request) throws Exception {
		ListAttrValueParam param = HttpRequestTools.parse(request, setOperatorCode.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrValueDomain attrValueDomain =  attrValueService.createAttrValue(param);		
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<AttrValueInfoResponse> updateAttrValue(HttpServletRequest request) throws Exception {
		ListAttrValueParam param = HttpRequestTools.parse(request, setOperatorCode.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrValueDomain attrValueDomain =  attrValueService.createAttrValue(param);		
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}
	
	
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<AttrValueInfoResponse> removeAttrValue(HttpServletRequest request) throws Exception {
		ListAttrValueParam param = HttpRequestTools.parse(request, setOperatorCode.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrValueDomain attrValueDomain =  attrValueService.createAttrValue(param);		
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}
}
