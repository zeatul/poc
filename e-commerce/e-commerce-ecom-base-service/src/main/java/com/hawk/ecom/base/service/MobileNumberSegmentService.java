package com.hawk.ecom.base.service;

import org.springframework.stereotype.Service;

import com.hawk.ecom.base.persist.domainex.MobileNumberSegmentExDomain;
import com.hawk.ecom.base.persist.mapperex.MobileNumberSegmentExMapper;

@Service
public class MobileNumberSegmentService {
	
	private MobileNumberSegmentExMapper mobileNumberSegmentExMapper;
	
	public MobileNumberSegmentExDomain queryMobileNumberSegment(String mobileNumber){		
		String mobileNumberPrefix = mobileNumber.substring(0,7);
		return mobileNumberSegmentExMapper.queryComposeMobileNumberSegment(mobileNumberPrefix);
	}

}
