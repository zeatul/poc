package com.hawk.ecom.svp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.mapper.BsiCashCouponMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiCashCouponService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BsiCashCouponMapper  bsiCashCouponMapper;
	
	public BsiCashCouponDomain loadByCode(String bsiCashCouponCode){
		if (StringTools.isNullOrEmpty(bsiCashCouponCode)){
			logger.error("bsiCashCouponCode is empty");
			return null;
		}
		
		bsiCashCouponCode = bsiCashCouponCode.trim();
		
		MybatisParam params = new MybatisParam().put("bsiCashCouponCode", bsiCashCouponCode);
		
		return MybatisTools.sigle( bsiCashCouponMapper.loadDynamic(params));
	}
	
	public int update(BsiCashCouponDomain bsiCashCouponDomain){
		if (bsiCashCouponDomain == null){
			logger.error("bsiCashCouponDomain is null");
			return 0;
		}
		return bsiCashCouponMapper.update(bsiCashCouponDomain);
	}
}
