package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/sku")
@CrossOrigin
public class SkuAdminController {
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/sku controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<ProductInfoResponse> createSku(HttpServletRequest request) throws Exception {
		CreateProductParam param = HttpRequestTools.parse(request, CreateProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		ProductDomain productDomain = productService.createProduct(param);
		return SuccessResponse.build(DomainTools.copy(productDomain, ProductInfoResponse.class));
	}
}
