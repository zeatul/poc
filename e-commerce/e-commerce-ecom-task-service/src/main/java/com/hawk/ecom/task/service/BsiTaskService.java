package com.hawk.ecom.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.service.BsiOuterService;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiTaskService {
	
	
	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService ;
	
	@Autowired
	private BsiOuterService bsiOuterService;
	
	public void buyBsi(String taskCode){
		if (StringTools.isNullOrEmpty(taskCode)){
			throw new RuntimeException("taskCode is null");
		}
		
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailDeliveryDataService.loadByTaskCode(taskCode);
		
		if (!(orderDetailDeliveryDataDomain.getTaskStatus() == ConstOrder.TaskStatus.EXECUTE_FAILED || 
				orderDetailDeliveryDataDomain.getTaskStatus() == ConstOrder.TaskStatus.UN_EXECUTE )){
			throw new RuntimeException("作业状态不是未执行或执行失败，不能继续操作，taskCode="+taskCode);
		}
		
		if (orderDetailDeliveryDataDomain.getExecTimes() >= orderDetailDeliveryDataDomain.getMaxExecTimes()){
			throw new RuntimeException("作业执行次数超过阈值,不能继续操作，taskCode="+taskCode);
		}
		
		BsiO
	}

}
