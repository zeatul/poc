package com.hawk.ecom.task.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.ChargeDataService;
import com.hawk.ecom.outer.service.ChargeDataService.ConstChargeNotifyStatus;
import com.hawk.ecom.outer.service.ChargeResult;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailDeliveryDataExMapper;
import com.hawk.ecom.trans.persist.mapperex.OrderDetailExMapper;
import com.hawk.ecom.trans.request.ChargeDataNotifyRequest;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.ecom.trans.service.OrderService;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;
import com.hawk.framework.utility.tools.SystemTools;



@Service
public class ChargeDataTaskService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataExMapper orderDetailDeliveryDataExMapper;
	
	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;
	
	@Autowired
	private OrderDetailExMapper orderDetailExMapper;
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;
	
	@Autowired
	private ChargeDataService chargeDataService;
	
	private String buildChargeTaskKey(String taskCode){
		return StringTools.concatWithSymbol("_", ChargeDataTaskService.class.getSimpleName(),taskCode);
	}
	
	public void chargeData(String taskCode) {
		if (StringTools.isNullOrEmpty(taskCode)){
			logger.error("taskCode is empty");
			return ;
		}
		/**
		 * 取缓存锁
		 */
		if (!cacheService.setnx(buildChargeTaskKey(taskCode), taskCode, 600-5)){
			logger.error("Failed to get the redis lock,taskCode={}",taskCode);
			return ;
		}
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailDeliveryDataService.loadByTaskCode(taskCode);
		String mobileNumber = orderDetailDeliveryDataDomain.getBenefMobileNumber();
		String productCode = orderDetailDeliveryDataDomain.getOuterProductId();
		if (orderDetailDeliveryDataDomain.getExecTimes() != 0){
			logger.error("The task({}) has been executed before. The charge data task only allow execute once!!!",taskCode);
			return ;
		}
		OrderDetailDeliveryDataDomain updateDomain = new OrderDetailDeliveryDataDomain();
		try {			
			updateDomain.setId(orderDetailDeliveryDataDomain.getId());
			updateDomain.setExecTimes(1);
			updateDomain.setLastExecBeginTime(new Date());
			updateDomain.setLastExecComputer(SystemTools.hostname());
			updateDomain.setLastExecProcessId(SystemTools.processId());
			ChargeResult chargeResult = chargeDataService.charge(mobileNumber, productCode, taskCode);
			
			updateDomain.setTaskStatus(chargeResult.isSuccess()?ConstOrder.TaskStatus.SUCCESS_TASK:ConstOrder.TaskStatus.FAILURE_TASK);
			
			updateDomain.setLastExecRtnCode(chargeResult.getCode());
			updateDomain.setLastExecRtnMsg(chargeResult.getMsg());

			
			
			
		} catch (Exception e) {
			updateDomain.setLastExecRtnCode(e.getClass().getSimpleName());
			updateDomain.setLastExecRtnMsg(e.getMessage());
			logger.error("chargeDataService.charge() failed",e); 
		}finally{
			updateDomain.setLastExecEndTime(new Date());
			orderDetailDeliveryDataMapper.update(updateDomain);
		}
	}

	
}
