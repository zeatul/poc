package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.constant.ConstCouponParameter;
import com.hawk.ecom.svp.persist.domain.BsiCashCouponDomain;
import com.hawk.ecom.svp.persist.mapper.BsiCashCouponMapper;
import com.hawk.ecom.svp.persist.mapperex.BsiCashCouponExMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiCashCouponService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BsiCashCouponMapper  bsiCashCouponMapper;
	
	@Autowired
	private BsiCashCouponExMapper  bsiCashCouponExMapper;
	
	public BsiCashCouponDomain loadByCode(String bsiCashCouponCode){
		if (StringTools.isNullOrEmpty(bsiCashCouponCode)){
			logger.error("bsiCashCouponCode is empty");
			return null;
		}
		
		bsiCashCouponCode = bsiCashCouponCode.trim();
		
		MybatisParam params = new MybatisParam().put("bsiCashCouponCode", bsiCashCouponCode);
		
		return MybatisTools.single( bsiCashCouponMapper.loadDynamic(params));
	}
	
	/**
	 * 查询5分钟之前的状态为激活中的数据
	 * @return
	 */
	public List<BsiCashCouponDomain>  counponForJob(){
		Date curDate = new Date();
		curDate = DateTools.addMinutes(curDate, -5);
		return bsiCashCouponExMapper.counponForJob(ConstCouponParameter.CouopnStatus.ACTIVVATING, curDate);
	}
	
	public int update(BsiCashCouponDomain bsiCashCouponDomain){
		if (bsiCashCouponDomain == null){
			logger.error("bsiCashCouponDomain is null");
			return 0;
		}
		return bsiCashCouponMapper.update(bsiCashCouponDomain);
	}
}
