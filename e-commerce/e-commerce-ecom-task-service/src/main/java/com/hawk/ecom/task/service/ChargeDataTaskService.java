package com.hawk.ecom.task.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.ChargeDataService;
import com.hawk.ecom.outer.service.chargeData.ChargeResult;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.framework.utility.tools.StringTools;
import com.hawk.framework.utility.tools.SystemTools;



@Service
public class ChargeDataTaskService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;
	
	
	
	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;
	
	@Autowired
	private ChargeDataService chargeDataService;
	
	
	
	public void chargeData(String taskCode) {
		if (StringTools.isNullOrEmpty(taskCode)){
			throw new RuntimeException("taskCode is null");
		}
		
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailDeliveryDataService.loadByTaskCode(taskCode);
		String mobileNumber = orderDetailDeliveryDataDomain.getBenefMobileNumber();
		String productCode = orderDetailDeliveryDataDomain.getOuterProductId();
		if (orderDetailDeliveryDataDomain.getExecTimes() != 0){
			throw new RuntimeException("The task has been executed before. The charge data task only allow execute once,taksCode="+taskCode);
		}
		OrderDetailDeliveryDataDomain updateDomain = new OrderDetailDeliveryDataDomain();
		try {			
			updateDomain.setId(orderDetailDeliveryDataDomain.getId());
			updateDomain.setExecTimes(orderDetailDeliveryDataDomain.getExecTimes()+1);
			updateDomain.setLastExecBeginTime(new Date());
			updateDomain.setLastExecComputer(SystemTools.hostname());
			updateDomain.setLastExecProcessId(SystemTools.processId());
			ChargeResult chargeResult = chargeDataService.charge(mobileNumber, productCode, taskCode);
			
			updateDomain.setTaskStatus(chargeResult.isSuccess()?ConstOrder.TaskStatus.SUCCESS_TASK:ConstOrder.TaskStatus.FAILURE_TASK);
			if (updateDomain.getTaskStatus()  == ConstOrder.TaskStatus.SUCCESS_TASK ){
				updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.PROCESSING);
			}else{
				updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.FAILURE);
			}
			
			updateDomain.setLastExecRtnCode(chargeResult.getCode());
			updateDomain.setLastExecRtnMsg(chargeResult.getMsg());

			
			
			
		} catch (Exception e) {
			logger.error("Failed to execute ChargeDataTaskService.charge(),taskCode="+taskCode,e); 
			updateDomain.setLastExecRtnCode(e.getClass().getSimpleName());
			updateDomain.setLastExecRtnMsg(e.getMessage());
			updateDomain.setTaskStatus(ConstOrder.TaskStatus.EXECUTE_FAILED);
			updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.FAILURE);
			
		}finally{
			updateDomain.setLastExecEndTime(new Date());
			orderDetailDeliveryDataMapper.updateWithoutNull(updateDomain);
		}
	}

	
}
