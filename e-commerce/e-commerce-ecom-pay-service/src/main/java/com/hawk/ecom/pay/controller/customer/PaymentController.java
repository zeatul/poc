package com.hawk.ecom.pay.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hawk.ecom.pay.constant.ConstPay;
import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.pay.request.TradeParam;
import com.hawk.ecom.pay.service.AlipayConfig;
import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.pay.service.WXPayService;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.HttpResponseHandler;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@RestController
@RequestMapping("/ecom/pay")
@CrossOrigin
public class PaymentController {

	

	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private WXPayService wxpayService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/pay controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/wap", method = POST)
	public void wapPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PayParam param = HttpRequestTools.parse(request, PayParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());		
		param.setWap(true);
		
		/**
		 * 微信支付需要openid(公众号支付）和客户端ip
		 */
		param.setOpenid(null);
		param.setIp(HttpRequestTools.getIp(request));
		
		
		String result = paymentService.pay(param);
		if (param.getPaymentCategoryCode().equalsIgnoreCase(ConstPay.PayCategoryCode.ALIPAY)){
			result = buildPayHtml(result);
		}
		
		HttpResponseHandler.printHtmlASAP(response, result);

	}
	
	private static int index = 10000;
	@RequestMapping(value = "/wxpay/test", method = GET)
	public String testWxPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Referer", "https://vstst.fexie.com.cn");
//		String ip = "101.90.253.43";
		String ip = HttpRequestTools.getIp(request);
		
		String openid = null;
		String wxPayType = WXPayService.WXPayType.H5;
		TradeParam tradeParam = new TradeParam();
		tradeParam.setBody("辣条一包");
		tradeParam.setOutTradeNo(DateTools.convert(new Date(), "yyyyMMdd")+ (++index));
		tradeParam.setSubject("辣条一包");
		tradeParam.setTotalAmount(new BigDecimal("0.01"));
		
		String url = wxpayService.pay(tradeParam, wxPayType, openid,ip );		

		return url;
	}
	
	
	@RequestMapping(value = "/wap2", method = GET)
	public void wapPay2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PayParam param = new PayParam();
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		String paymentCategoryCode = request.getParameter("paymentCategoryCode");
		param.setOrderId(orderId);
		param.setPaymentCategoryCode(paymentCategoryCode);
		
		param.setOpenid(null);
		param.setIp(HttpRequestTools.getIp(request));
		
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setWap(true);
		String result = paymentService.pay(param);
		if (param.getPaymentCategoryCode().equalsIgnoreCase(ConstPay.PayCategoryCode.ALIPAY)){
			result = buildPayHtml(result);
		}
		
		HttpResponseHandler.printHtmlASAP(response, result);

	}

	private String buildPayHtml(String form){
		StringBuilder sb= StringTools.getThreadSafeStringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta charset=\"UTF-8\">");
		sb.append("<title>pay</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(form);
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	

}
