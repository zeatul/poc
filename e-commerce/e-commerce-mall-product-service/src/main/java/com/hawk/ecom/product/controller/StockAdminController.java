package com.hawk.ecom.product.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.product.persist.domain.SkuDomain;
import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.request.CreateSkuParam;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.product.response.SkuInfoResponse;
import com.hawk.ecom.product.response.StockInfoResponse;
import com.hawk.ecom.product.service.SkuService;
import com.hawk.ecom.product.service.StockService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/mall/admin/product/stock")
@CrossOrigin
public class StockAdminController {
	
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /mall/admin/product/stock controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/create", method = POST)
	public WebResponse<StockInfoResponse> createStock(HttpServletRequest request) throws Exception {
		CreateStockParam param = HttpRequestTools.parse(request, CreateStockParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		StockDomain stockDomain = stockService.createStock(param);
		return SuccessResponse.build(DomainTools.copy(stockDomain, StockInfoResponse.class));
	} 

}
