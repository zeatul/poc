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

import com.hawk.ecom.product.persist.domain.AttrNameDomain;
import com.hawk.ecom.product.request.CreateAttrNameParam;
import com.hawk.ecom.product.request.ListAttrNameParam;
import com.hawk.ecom.product.request.LoadAttrNameParam;
import com.hawk.ecom.product.request.RemoveAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameParam;
import com.hawk.ecom.product.request.UpdateAttrNameStatusParam;
import com.hawk.ecom.product.response.AttrNameInfoResponse;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.product.service.AttrNameService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/attr/name")
@CrossOrigin
public class AttrNameAdminController {
	
	@Autowired
	private AttrNameService attrNameService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/attr/name controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<AttrNameInfoResponse> createAttrName(HttpServletRequest request) throws Exception {
		CreateAttrNameParam param = HttpRequestTools.parse(request, CreateAttrNameParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		AttrNameDomain attrNameDomain =  attrNameService.createAttrName(param);		
		return SuccessResponse.build(DomainTools.copy(attrNameDomain, AttrNameInfoResponse.class));
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<ResponseData> updateAttrName(HttpServletRequest request) throws Exception {
		UpdateAttrNameParam param = HttpRequestTools.parse(request, UpdateAttrNameParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrNameService.updateAttrName(param);
		return SuccessResponse.build(null);
	}
	
	
	
	@RequestMapping(value = "/list", method = {POST})
	public WebResponse<MultiResponse<AttrNameInfoResponse>> listAttrName(HttpServletRequest request) throws Exception {
		ListAttrNameParam param = HttpRequestTools.parse(request, ListAttrNameParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		
		MultiResponse<AttrNameInfoResponse> result = new MultiResponse<AttrNameInfoResponse>(MybatisTools.copy(attrNameService.listAttrName(param), AttrNameInfoResponse.class));
		return SuccessResponse.build(result);
		
	
	} 
	
	@RequestMapping(value = "/load/id/{id}", method = {POST,GET})
	public WebResponse<AttrNameInfoResponse> listAttrName(@PathVariable Integer id) throws Exception {
		LoadAttrNameParam param = new LoadAttrNameParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		AttrNameDomain attrNameDomain =  attrNameService.loadAttrName(param);		
		return SuccessResponse.build(DomainTools.copy(attrNameDomain, AttrNameInfoResponse.class));
	} 
	
	
	@RequestMapping(value = "/status/update", method= POST)
	public WebResponse<ResponseData> updateAttrNameStatus(HttpServletRequest request) throws Exception {
		UpdateAttrNameStatusParam param = HttpRequestTools.parse(request, UpdateAttrNameStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrNameService.updateAttrNameStatus(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<ResponseData> removeAttrName(HttpServletRequest request) throws Exception {
		RemoveAttrNameParam param = HttpRequestTools.parse(request, RemoveAttrNameParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		attrNameService.removeAttrName(param);
		return SuccessResponse.build(null);
	}
}
