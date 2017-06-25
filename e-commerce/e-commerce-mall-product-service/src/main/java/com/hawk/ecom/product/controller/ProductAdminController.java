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
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.ecom.product.request.LoadProductParam;
import com.hawk.ecom.product.request.RemoveProductParam;
import com.hawk.ecom.product.request.UpdateProductParam;
import com.hawk.ecom.product.request.UpdateProductStatusParam;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.product.service.ProductService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/product")
@CrossOrigin
public class ProductAdminController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/product controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/create", method = POST)
	public WebResponse<ProductInfoResponse> createProduct(HttpServletRequest request) throws Exception {
		CreateProductParam param = HttpRequestTools.parse(request, CreateProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		ProductDomain productDomain = productService.createProduct(param);
		return SuccessResponse.build(DomainTools.copy(productDomain, ProductInfoResponse.class));
	}

	@RequestMapping(value = "/update", method = POST)
	public WebResponse<ResponseData> updateProduct(HttpServletRequest request) throws Exception {
		UpdateProductParam param = HttpRequestTools.parse(request, UpdateProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		productService.updateProduct(param);
		return SuccessResponse.build(null);
	}

	@RequestMapping(value = "/status/update", method = POST)
	public WebResponse<ResponseData> updateProductStatus(HttpServletRequest request) throws Exception {
		UpdateProductStatusParam param = HttpRequestTools.parse(request, UpdateProductStatusParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		productService.updateProductStatus(param);
		return SuccessResponse.build(null);
	}

	@RequestMapping(value = "/remove", method = POST)
	public WebResponse<ResponseData> removeProduct(HttpServletRequest request) throws Exception {
		RemoveProductParam param = HttpRequestTools.parse(request, RemoveProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		productService.removeProduct(param);
		return SuccessResponse.build(null);
	}

	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<ProductInfoResponse>> ListProduct(HttpServletRequest request) throws Exception {
		ListProductParam param = HttpRequestTools.parse(request, ListProductParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<ProductDomain> wrap = productService.listProduct(param);

		MultiResponse<ProductInfoResponse> result = new MultiResponse<ProductInfoResponse>(MybatisTools.copy(wrap, ProductInfoResponse.class));
		return SuccessResponse.build(result);
	}
	
	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<ProductInfoResponse> loadProduct (@PathVariable Integer id) throws Exception{
		LoadProductParam param = new LoadProductParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		return SuccessResponse.build(DomainTools.copy(productService.loadProduct(param), ProductInfoResponse.class));
	}
}
