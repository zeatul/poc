package com.hawk.ecom.product.controller;

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

import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.ExchangeCategoryOrderParam;
import com.hawk.ecom.product.request.ListCategoryParam;
import com.hawk.ecom.product.request.LoadCategoryParam;
import com.hawk.ecom.product.request.RemoveCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryParam;
import com.hawk.ecom.product.request.UpdateCategoryStatusParam;
import com.hawk.ecom.product.request.UpdateCategoryTemplateStatusParam;
import com.hawk.ecom.product.response.CategoryInfoResponse;
import com.hawk.ecom.product.service.CategoryService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/category")
@CrossOrigin
public class CategoryAdminController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/category controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<CategoryInfoResponse> createCategory(HttpServletRequest request) throws Exception {
		CreateCategoryParam param = HttpRequestTools.parse(request, CreateCategoryParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		CategoryDomain categoryDomain =  categoryService.createCategory(param);
		CategoryInfoResponse result = DomainTools.copy(categoryDomain, CategoryInfoResponse.class);
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<CategoryInfoResponse> updateCategory(HttpServletRequest request) throws Exception {
		UpdateCategoryParam param = HttpRequestTools.parse(request, UpdateCategoryParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		categoryService.updateCategory(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/status/update", method = POST)
	public WebResponse<ResponseData> updateCategoryStatus(HttpServletRequest request) throws Exception {
		UpdateCategoryStatusParam param = HttpRequestTools.parse(request, UpdateCategoryStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		categoryService.updateCategoryStatus(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/template/status/update", method = POST)
	public WebResponse<ResponseData> updateCategoryTemplateStatus(HttpServletRequest request) throws Exception {
		UpdateCategoryTemplateStatusParam param = HttpRequestTools.parse(request, UpdateCategoryTemplateStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		categoryService.updateCategoryTemplateStatus(param);
		return SuccessResponse.build(null);
	}
	
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<CategoryInfoResponse>> ListCategory(HttpServletRequest request) throws Exception {
		ListCategoryParam param = HttpRequestTools.parse(request, ListCategoryParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		List<CategoryDomain> categoryDomainList =  categoryService.listCategory(param);
		
		MultiResponse<CategoryInfoResponse> result = new MultiResponse<CategoryInfoResponse>(DomainTools.copy(categoryDomainList, CategoryInfoResponse.class));
		return SuccessResponse.build(result);
	} 
	
	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<CategoryInfoResponse> loadCategory(@PathVariable Integer id) throws Exception {
		LoadCategoryParam param = new LoadCategoryParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		CategoryDomain categoryDomain = categoryService.loadCategory(param);
		CategoryInfoResponse result = DomainTools.copy(categoryDomain, CategoryInfoResponse.class);
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<ResponseData> removeCategory(HttpServletRequest request) throws Exception {
		RemoveCategoryParam param = HttpRequestTools.parse(request, RemoveCategoryParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		categoryService.removeCategory(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/order/exchange", method = POST)
	public WebResponse<ResponseData> exchangeCategoryOrder(HttpServletRequest request) throws Exception {
		ExchangeCategoryOrderParam param = HttpRequestTools.parse(request, ExchangeCategoryOrderParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		categoryService.exchangeCategoryOrder(param);
		return SuccessResponse.build(null);
	}
}
