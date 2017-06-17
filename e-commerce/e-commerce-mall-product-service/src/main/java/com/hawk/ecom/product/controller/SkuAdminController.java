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

import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.product.response.SkuInfoResponse;
import com.hawk.ecom.product.service.SkuService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.UpdateSkuParam;

@RestController
@RequestMapping("/mall/admin/product/sku")
@CrossOrigin
public class SkuAdminController {
	
	@Autowired
	private SkuService skuService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/sku controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<SkuInfoResponse> createSku(HttpServletRequest request) throws Exception {
		CreateSkuParam param = HttpRequestTools.parse(request, CreateSkuParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		SkuDomain skuDomain = skuService.createSku(param);
		return SuccessResponse.build(DomainTools.copy(skuDomain, SkuInfoResponse.class));
	}
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<ResponseData> updateSku(HttpServletRequest request) throws Exception {
		UpdateSkuParam param = HttpRequestTools.parse(request, UpdateSkuParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		skuService.updateSku(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/listOfProduct/productId/{productId}", method = {GET,POST})
	public WebResponse<MultiResponse<SkuInfoResponse>> listSkuOfProduct (@PathVariable Long productId) throws Exception {
		
	}
	
	@RequestMapping(value = "/list", method = {GET,POST})
	public WebResponse<MultiResponse<SkuInfoResponse>> listSku(@PathVariable Long productId) throws Exception {
		
	}
	
	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<MultiResponse<SkuInfoResponse>> loadSku (@PathVariable Long id) throws Exception {
		
	}
}
