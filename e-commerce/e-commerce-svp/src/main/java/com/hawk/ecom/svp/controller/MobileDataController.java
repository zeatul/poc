package com.hawk.ecom.svp.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.svp.request.SignInParam;
import com.hawk.ecom.svp.service.MobileDataService;
import com.hawk.ecom.svp.utils.HttpRequestToolsForSignature;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

/**
 * 流量下单接口
 * @author pzhang1
 *
 */
@RestController
@RequestMapping("/svp/mobile")
public class MobileDataController {
	
	@Autowired
	private MobileDataService mobileDataService;
	
	@RequestMapping(value="/home",method = GET)
	public String home(){
		return "Welcome to charge mobile data!!!";
	}
	
	@RequestMapping(value = "/signin", method = POST)
	public WebResponse<ResponseData> signIn(HttpServletRequest request) throws IOException{
		SignInParam signInParam = HttpRequestToolsForSignature.parse(request, SignInParam.class);		
		mobileDataService.signIn(signInParam);		
		return SuccessResponse.build(null);
	}
	
	@RequestMapping(value="/unicom/exist/taskId/{taskId}",method = GET)
	public WebResponse<ResponseData> checkTaskId(@PathVariable String taskId){
		mobileDataService.checkTaskId(taskId);
		return SuccessResponse.build(null);
	}
}
