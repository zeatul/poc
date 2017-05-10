package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.constant.ConstChargeStatus;
import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.MobileDataOrderDetailMapper;
import com.hawk.ecom.svp.persist.mapperex.MobileDataOrderDetailExMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;


@Service
public class MobileDataOrderDetailService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MobileDataOrderDetailMapper mobileDataOrderDetailMapper;
	
	@Autowired
	private MobileDataOrderDetailExMapper mobileDataOrderDetailExMapper;
	
	public List<String> taskCodeForJob(){
		return mobileDataOrderDetailExMapper.taskCodeForJob(ConstChargeStatus.COMPLETE_FAILED, new Date());
	}
	
	
	public  MobileDataOrderDetailDomain loadByTaskCode(String chargeTaskCode){
		
		if (StringTools.isNullOrEmpty(chargeTaskCode))
			throw new RuntimeException("任务编号为空");
		
		MybatisParam params = new MybatisParam().put("chargeTaskCode", chargeTaskCode);
		List<MobileDataOrderDetailDomain> list = mobileDataOrderDetailMapper.loadDynamic(params);
		
		return MybatisTools.single(list);
	}
	
	public MobileDataOrderDetailDomain loadByOrderCode(String orderCode){
		if (StringTools.isNullOrEmpty(orderCode)){
			logger.error("The orderCode is null");
			return null;
		}
		MybatisParam params = new MybatisParam().put("orderCode", orderCode);
		return MybatisTools.single(mobileDataOrderDetailMapper.loadDynamic(params));
	}
	
	public int update(MobileDataOrderDetailDomain mobileDataOrderDetailDomain){
		if (mobileDataOrderDetailDomain == null){
			logger.error("The mobileDataOrderDetailDomain is null" );
			return 0;
		}
		return mobileDataOrderDetailMapper.update(mobileDataOrderDetailDomain);
	}

}
