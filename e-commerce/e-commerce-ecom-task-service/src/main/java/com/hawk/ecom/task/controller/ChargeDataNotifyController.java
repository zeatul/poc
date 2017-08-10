package com.hawk.ecom.task.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.outer.service.ChargeDataService;
import com.hawk.ecom.outer.service.chargeData.ChargeResult;
import com.hawk.ecom.task.service.ChargeDataNotifyService;
import com.hawk.ecom.trans.request.ChargeDataNotifyRequest;
import com.hawk.ecom.trans.response.ChargeDataNotifyResponse;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.HttpResponseHandler;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;

@RestController
@RequestMapping("/ecom/task/notify/chargeData")
@CrossOrigin
public class ChargeDataNotifyController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChargeDataNotifyService chargeDataTaskService;
	
	@Autowired
	private ChargeDataService chargeDataService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/task/chargeData controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/testCharge", method = GET)
	public void testCharge(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ChargeResult chargeResult = chargeDataService.charge("18666075617", "LC020U21010M", UUID.randomUUID().toString().replaceAll("-", ""));
		HttpResponseHandler.printHtmlASAP(response, JsonTools.toJsonString(chargeResult));
	}

	/**
	 * 供应商调用的通知接口,返回流量充值结果
	 * 
	 * @param suplierCode
	 * @throws Exception
	 */
	@RequestMapping(value = "/storeCode/{storeCode}/supplierCode/{supplierCode}", method = POST)
	public WebResponse<ChargeDataNotifyResponse> notify(@PathVariable String storeCode, @PathVariable String supplierCode, HttpServletRequest request) throws Exception {

		logger.info("recieve chargeData notify , storeCode={},supplierCode={}",storeCode,supplierCode);
		
		if (!"VD000001".equals(supplierCode)){
			throw new RuntimeException("supplierCode="+supplierCode+",is illegal");
		}
		if (!"ST000001".equals(storeCode)){
			throw new RuntimeException("storeCode="+storeCode+",is illegal");
		}
		
		ChargeDataNotifyRequest param = HttpRequestTools.parse(request, ChargeDataNotifyRequest.class);
		param.setSupplierCode(supplierCode);
		param.setStoreCode(storeCode);		
		
		chargeDataTaskService.notify(param);

		return SuccessResponse.build(ChargeDataNotifyResponse.getInstance());
	}

}
