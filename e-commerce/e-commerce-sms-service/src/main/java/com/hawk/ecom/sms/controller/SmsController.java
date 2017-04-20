package com.hawk.ecom.sms.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.sms.exception.FailedSendMessageException;
import com.hawk.ecom.sms.request.SendVeriCodeParam;
import com.hawk.ecom.sms.service.SendSmsResult;
import com.hawk.ecom.sms.service.SmsService;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/sms")
@CrossOrigin
public class SmsController {
	
	@Autowired
	private SmsService smsService;
	
	@RequestMapping(value = "/veriCode", method = {POST})
	public WebResponse<ResponseData> veriCode(HttpServletRequest request) throws Exception{
		SendVeriCodeParam sendVeriCodeParam = HttpRequestTools.parse(request, SendVeriCodeParam.class);
		SendSmsResult sendSmsResult = smsService.sendVeriCode(sendVeriCodeParam);
		if (!sendSmsResult.isSuccess())
			throw new FailedSendMessageException(sendSmsResult.getCode(),sendSmsResult.getMessage());
		return SuccessResponse.build(null);
	}

}
