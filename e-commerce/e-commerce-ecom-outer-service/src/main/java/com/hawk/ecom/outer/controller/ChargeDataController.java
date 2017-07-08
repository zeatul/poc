package com.hawk.ecom.outer.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.outer.request.ChargeDataNotifyRequest;
import com.hawk.ecom.outer.response.ChargeDataNotifyResponse;
import com.hawk.ecom.outer.service.ChargeDataService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/ecom/outer/chargeData")
@CrossOrigin
public class ChargeDataController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChargeDataService chargeDataService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/outer/chargeData controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	/**
	 * 供应商调用的通知接口,返回流量充值结果
	 * 
	 * @param suplierCode
	 * @throws Exception
	 */
	@RequestMapping(value = "/notify/storeCode/{storeCode}/supplierCode/{supplierCode}", method = POST)
	public WebResponse<ChargeDataNotifyResponse> notify(@PathVariable String storeCode, @PathVariable String supplierCode, HttpServletRequest request) throws Exception {
		logger.info("receive notify,storeCode={},supplierCode={}!!!", storeCode, supplierCode);
		if (!"ST000001".equals(storeCode)) {
			throw new RuntimeException("商户编号错误");
		}
		if (!"VD000001".equals(supplierCode)) {
			throw new RuntimeException("供应商编号错误");
		}
		ChargeDataNotifyRequest param = HttpRequestTools.parse(request, ChargeDataNotifyRequest.class);
		
		chargeDataService.notify(param);

		return SuccessResponse.build(ChargeDataNotifyResponse.getInstance());
	}

}
