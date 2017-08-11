package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.query.persist.domainex.ProductCategoryExDomain;
import com.hawk.ecom.query.persist.domainex.ProductSkuExDomain;
import com.hawk.ecom.query.request.ListSkuParam;
import com.hawk.ecom.query.request.LoadChargeDataProductParam;
import com.hawk.ecom.query.service.ProductQueryService;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;



@RestController
@RequestMapping("/ecom/query/product")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductQueryService productService;

	
	/**
	 * 列出
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/category/list", method = {POST,GET})
	public WebResponse<MultiResponse<ProductCategoryExDomain>> listCategory(HttpServletRequest request) throws Exception{
		List<ProductCategoryExDomain> list = productService.listCategory();
		return SuccessResponse.build(new MultiResponse<ProductCategoryExDomain>(list));
	}
	
	@RequestMapping(value = "/sku/list", method = {POST,GET})
	public WebResponse<MultiResponse<ProductSkuExDomain>> querySku(HttpServletRequest request) throws Exception{
		ListSkuParam param = HttpRequestTools.parse(request, ListSkuParam.class);
		PagingQueryResultWrap<ProductSkuExDomain> wrap = productService.querySku(param);

		MultiResponse<ProductSkuExDomain> result = new MultiResponse<ProductSkuExDomain>(wrap);
		return SuccessResponse.build(result);
	}
	
	/**
	 * 加载sku
	 * @param skuId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sku/load/id/{skuId}", method = {POST,GET})
	public WebResponse<ProductSkuExDomain> loadSku(@PathVariable Integer skuId) throws Exception{
		 return SuccessResponse.build(productService.loadSku(skuId));
	}
	
	/**
	 * 加载sku的价格和库存
	 * @param skuId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sku/loadSaleData/id/{skuId}", method = {POST,GET})
	public WebResponse<ProductSkuExDomain> loadSkuPriceAndQuantity(@PathVariable Integer skuId) throws Exception{
		return SuccessResponse.build(productService.loadSkuPriceAndQuantity(skuId));
	}
	
	
	

}
