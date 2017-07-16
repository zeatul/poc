package com.hawk.ecom.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.base.persist.domainex.MobileNumberSegmentExDomain;
import com.hawk.ecom.base.persist.mapperex.MobileNumberSegmentExMapper;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;

@Service
public class MobileNumberSegmentService {
	
	@Autowired
	private MobileNumberSegmentExMapper mobileNumberSegmentExMapper;
	
	@Valid
	public MobileNumberSegmentExDomain queryMobileNumberSegment(@NotEmpty("mobileNumber") String mobileNumber){		
		String mobileNumberPrefix = mobileNumber.substring(0,7);
		return mobileNumberSegmentExMapper.queryComposeMobileNumberSegment(mobileNumberPrefix);
	}

}
