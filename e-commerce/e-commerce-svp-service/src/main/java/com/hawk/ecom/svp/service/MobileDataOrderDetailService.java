package com.hawk.ecom.svp.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.MobileDataOrderDetailMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;


@Service
public class MobileDataOrderDetailService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MobileDataOrderDetailMapper mobileDataOrderDetailMapper;
	
	public  MobileDataOrderDetailDomain loadByTaskId(String chargeTaskCode){
		
		if (StringTools.isNullOrEmpty(chargeTaskCode))
			throw new RuntimeException("任务编号为空");
		
		MybatisParam params = new MybatisParam().put("chargeTaskCode", chargeTaskCode);
		List<MobileDataOrderDetailDomain> list = mobileDataOrderDetailMapper.loadDynamic(params);
		
		return MybatisTools.single(list);
	}
	
	public List<MobileDataOrderDetailDomain> loadByOrderId(Long orderId){
		if (orderId == null){
			logger.error("The orderId is null");
			return Collections.emptyList();
		}
		MybatisParam params = new MybatisParam().put("orderId", orderId);
		return mobileDataOrderDetailMapper.loadDynamic(params);
	}
	
	public int update(MobileDataOrderDetailDomain mobileDataOrderDetailDomain){
		if (mobileDataOrderDetailDomain == null){
			logger.error("The mobileDataOrderDetailDomain is null" );
			return 0;
		}
		return mobileDataOrderDetailMapper.update(mobileDataOrderDetailDomain);
	}

}
