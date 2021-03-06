package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.AttrValueDomain;
import com.hawk.ecom.product.persist.domainex.AttrValueExDomain;
import com.hawk.ecom.product.request.CreateAttrValueParam;
import com.hawk.ecom.product.request.ListAttrValueParam;
import com.hawk.ecom.product.request.LoadAttrValueParam;
import com.hawk.ecom.product.request.RemoveAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueParam;
import com.hawk.ecom.product.request.UpdateAttrValueStatusParam;
import com.hawk.ecom.product.response.AttrNameInfoResponse;
import com.hawk.ecom.product.response.AttrValueInfoResponse;
import com.hawk.ecom.product.service.AttrValueService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.sql.MybatisTools;
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
		AttrValueDomain attrValueDomain = attrValueService.createAttrValue(param);
		return SuccessResponse.build(DomainTools.copy(attrValueDomain, AttrValueInfoResponse.class));
	}

	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<AttrValueInfoResponse>> listAttrValue(HttpServletRequest request) throws Exception {
		ListAttrValueParam param = HttpRequestTools.parse(request, ListAttrValueParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		MultiResponse<AttrValueInfoResponse> result = new MultiResponse<AttrValueInfoResponse>(MybatisTools.copy(attrValueService.listAttrValue(param), AttrValueInfoResponse.class));
		return SuccessResponse.build(result);
		
	}

	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<AttrValueInfoResponse> loadAttrValue(@PathVariable Integer id) throws Exception {
		LoadAttrValueParam param = new LoadAttrValueParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		AttrValueExDomain attrValueExDomain = attrValueService.loadAttrValue(param);
		return SuccessResponse.build(DomainTools.copy(attrValueExDomain, AttrValueInfoResponse.class));
	}

	@RequestMapping(value = "/update", method = POST)
	public WebResponse<AttrValueInfoResponse> updateAttrValue(HttpServletRequest request) throws Exception {
		UpdateAttrValueParam param = HttpRequestTools.parse(request, UpdateAttrValueParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrValueService.updateAttrValue(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/status/update", method = POST)
	public WebResponse<AttrValueInfoResponse> updateAttrValueStatus(HttpServletRequest request) throws Exception {
		UpdateAttrValueStatusParam param = HttpRequestTools.parse(request, UpdateAttrValueStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrValueService.updateAttrValueStatus(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<AttrValueInfoResponse> removeAttrValue(HttpServletRequest request) throws Exception {
		RemoveAttrValueParam param = HttpRequestTools.parse(request, RemoveAttrValueParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrValueService.removeAttrValue(param);
		return SuccessResponse.build(null);
	}
}
