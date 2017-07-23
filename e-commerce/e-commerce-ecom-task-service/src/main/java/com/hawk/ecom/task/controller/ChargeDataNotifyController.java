package com.hawk.ecom.task.controller;

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

import com.hawk.ecom.task.request.ChargeDataNotifyRequest;
import com.hawk.ecom.task.response.ChargeDataNotifyResponse;
import com.hawk.ecom.task.service.ChargeDataTaskService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.JsonTools;

@RestController
@RequestMapping("/ecom/task/notifiy/chargeData")
@CrossOrigin
public class ChargeDataNotifyController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ChargeDataTaskService chargeDataTaskService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/task/chargeData controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
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
