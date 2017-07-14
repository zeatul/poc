package com.hawk.ecom.base.persist.mapperex;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.base.persist.domainex.MobileNumberSegmentExDomain;

public interface MobileNumberSegmentExMapper {
	public MobileNumberSegmentExDomain queryComposeMobileNumberSegment(@Param("mobileNumberPrefix") String mobileNumberPrefix);
}
