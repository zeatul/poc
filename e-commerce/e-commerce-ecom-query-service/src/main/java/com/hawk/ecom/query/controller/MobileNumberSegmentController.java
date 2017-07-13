package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.base.persist.domainex.MobileNumberSegmentExDomain;
import com.hawk.ecom.base.service.MobileNumberSegmentService;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;

@RestController
@RequestMapping("/ecom/query/mobile")
@CrossOrigin
public class MobileNumberSegmentController {
	
	@Autowired
	private MobileNumberSegmentService mobileNumberSegmentService;

	@RequestMapping(value = "/segment/mobileNumber/{mobileNumber}", method = {POST,GET})
	public WebResponse<MobileNumberSegmentExDomain> loadMobileNumberSegment(@PathVariable String mobileNumber) throws Exception{
		MobileNumberSegmentExDomain result = mobileNumberSegmentService.queryMobileNumberSegment(mobileNumber);
		if (result == null){
			throw new RuntimeException("未找到手机号对应的运营商与地区信息");
		}
		return SuccessResponse.build(result);
	}
}
