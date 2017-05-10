package com.hawk.ecom.svp.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;

public interface BsiCashCouponExMapper {
	
	public List<BsiCashCouponDomain> counponForJob(@Param("bsiCashCouponStatus") Integer bsiCashCouponStatus,@Param("curDate") Date curDate);

}
