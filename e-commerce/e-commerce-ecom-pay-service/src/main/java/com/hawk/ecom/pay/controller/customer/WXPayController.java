package com.hawk.ecom.pay.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pay.response.AlipayReturnResponse;
import com.hawk.ecom.pay.response.WXPayReturnResponse;
import com.hawk.ecom.pay.service.WXPayService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.HttpResponseHandler;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;

@RestController
@RequestMapping("/ecom/pay/wxpay")
@CrossOrigin
public class WXPayController {
	
	@Autowired
	private WXPayService wxpayService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/pay/wxpay controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}
	
	@RequestMapping(value = "/notify", method = { POST, GET })
	public void wxpayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("+++++wxpay notify start!!!!");
		String notifyXml = HttpRequestTools.parse(request, String.class);
		logger.info("wapy notify xml = {})",notifyXml);
		String x = wxpayService.notify(notifyXml);
		HttpResponseHandler.printHtmlASAP(response, x);
	}
	
	@RequestMapping(value = "/wap/return", method = { POST, GET })
	public WebResponse<WXPayReturnResponse> wapReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("+++++wxpay wap return start!!!!");
		WXPayReturnResponse wxpayReturnResponse = new WXPayReturnResponse();
		return SuccessResponse.build(wxpayReturnResponse);
	}
}
