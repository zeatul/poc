package com.hawk.ecom.pay.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.pay.service.AlipayConfig;
import com.hawk.ecom.pay.service.PaymentService;
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

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/pay controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/wap", method = POST)
	public void wapPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PayParam param = HttpRequestTools.parse(request, PayParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setWap(true);
		String result = buildPayHtml(paymentService.pay(param));
		HttpResponseHandler.printHtmlASAP(response, result);

	}
	
	@RequestMapping(value = "/checkUnfinishedPayment/paymentBillId/{paymentBillId}", method = {POST,GET})
	public WebResponse<ResponseData> checkUnfinishedPayment(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer paymentBillId) throws Exception{
		paymentService.checkUnfinishedPayment(paymentBillId);
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value = "/wap2", method = GET)
	public void wapPay2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PayParam param = new PayParam();
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		String paymentCategoryCode = request.getParameter("paymentCategoryCode");
		param.setOrderId(orderId);
		param.setPaymentCategoryCode(paymentCategoryCode);
		
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setWap(true);
		String result = buildPayHtml(paymentService.pay(param));
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
