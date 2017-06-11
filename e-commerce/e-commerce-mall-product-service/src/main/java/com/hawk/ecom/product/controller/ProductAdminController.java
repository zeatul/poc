package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.CategoryDomain;
import com.hawk.ecom.product.request.CreateCategoryParam;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.response.CategoryInfoResponse;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/product")
@CrossOrigin
public class ProductAdminController {
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/product controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<ProductInfoResponse> createProduct(HttpServletRequest request) throws Exception {
		CreateProductParam param = HttpRequestTools.parse(request, CreateProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		CategoryDomain categoryDomain =  categoryService.createCategory(param);
		CategoryInfoResponse result = DomainTools.copy(categoryDomain, CategoryInfoResponse.class);
		return SuccessResponse.build(result);
	}
}
