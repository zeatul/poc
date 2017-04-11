package com.hawk.ecom.svp.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiOrderDetailService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BsiOrderDetailMapper bsiOrderDetailMapper;
	
	public BsiOrderDetailDomain loadByCouponCode(String bsiCashCouponCode){
		if (StringTools.isNullOrEmpty(bsiCashCouponCode)){
			logger.error("The bsiCashCouponCode is empty");
			return null;
		}
		
		MybatisParam params = new MybatisParam().put("bsiCashCouponCode", bsiCashCouponCode);
		
		return MybatisTools.sigle(bsiOrderDetailMapper.loadDynamic(params));
		
	}
	
	public List<BsiOrderDetailDomain> loadByOrderId(Long orderId){
		if (orderId == null){
			logger.error("The orderId is null");
			return Collections.emptyList();
		}
		
		MybatisParam params = new MybatisParam().put("orderId", orderId);
		return bsiOrderDetailMapper.loadDynamic(params);
	}
}
