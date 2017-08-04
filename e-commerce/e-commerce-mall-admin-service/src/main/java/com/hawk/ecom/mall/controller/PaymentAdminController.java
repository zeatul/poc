package com.hawk.ecom.mall.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/mall/admin/pay")
@CrossOrigin
public class PaymentAdminController {

	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 检查未完成的支付单，通过向支付公司查询，来确认支付结果
	 * @param request
	 * @param response
	 * @param paymentBillId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUnfinishedPayment/paymentBillId/{paymentBillId}", method = {POST,GET})
	public WebResponse<ResponseData> checkUnfinishedPayment(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer paymentBillId) throws Exception{
		paymentService.checkUnfinishedPayment(paymentBillId);
		return SuccessResponse.build(null);
	}
}
