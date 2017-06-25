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

import com.hawk.ecom.product.persist.domain.StockDomain;
import com.hawk.ecom.product.request.CreateStockParam;
import com.hawk.ecom.product.request.ListStockParam;
import com.hawk.ecom.product.request.LoadPicParam;
import com.hawk.ecom.product.request.LoadStockParam;
import com.hawk.ecom.product.request.UpdateStockParam;
import com.hawk.ecom.product.response.PicInfoResponse;
import com.hawk.ecom.product.response.StockInfoResponse;
import com.hawk.ecom.product.service.StockService;
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
	
	@RequestMapping(value = "/update", method = POST)
	public WebResponse<ResponseData> updateStock(HttpServletRequest request) throws Exception {
		UpdateStockParam param = HttpRequestTools.parse(request, UpdateStockParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		stockService.updateStock(param);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/list", method = POST)
	public WebResponse<MultiResponse<StockInfoResponse>> listStock(HttpServletRequest request) throws Exception {
		ListStockParam param = HttpRequestTools.parse(request, ListStockParam.class);
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		PagingQueryResultWrap<StockDomain> wrap = stockService.listStock(param);

		MultiResponse<StockInfoResponse> result = new MultiResponse<StockInfoResponse>(MybatisTools.copy(wrap, StockInfoResponse.class));
		return SuccessResponse.build(result);
	} 
	
	@RequestMapping(value = "/load/id/{id}", method = {GET,POST})
	public WebResponse<StockInfoResponse> loadPic (@PathVariable Integer id) throws Exception{
		LoadStockParam param = new LoadStockParam();
		param.setOperatorCode(AuthThreadLocal.getUserCode());
		param.setId(id);
		return SuccessResponse.build(DomainTools.copy(stockService.loadStock(param), StockInfoResponse.class));
	}

}
