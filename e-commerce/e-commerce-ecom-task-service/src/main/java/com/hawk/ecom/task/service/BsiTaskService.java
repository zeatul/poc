package com.hawk.ecom.task.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.outer.response.CreateBsiOrderResponse;
import com.hawk.ecom.outer.service.BsiOuterService;
import com.hawk.ecom.outer.service.BsiOuterService.BsiOrder;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.ecom.trans.constant.ConstOrder;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.ecom.trans.service.OrderDetailDeliveryDataService;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.StringTools;
import com.hawk.framework.utility.tools.SystemTools;

@Service
public class BsiTaskService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrderDetailDeliveryDataService orderDetailDeliveryDataService;
	
	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;

	@Autowired
	private BsiOuterService bsiOuterService;

	public void buyBsi(String taskCode) {
		if (StringTools.isNullOrEmpty(taskCode)) {
			throw new RuntimeException("taskCode is null");
		}

		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = orderDetailDeliveryDataService.loadByTaskCode(taskCode);

		if (!(orderDetailDeliveryDataDomain.getTaskStatus() == ConstOrder.TaskStatus.EXECUTE_FAILED
				|| orderDetailDeliveryDataDomain.getTaskStatus() == ConstOrder.TaskStatus.UN_EXECUTE)) {
			throw new RuntimeException("作业状态不是未执行或执行失败，不能继续操作，taskCode=" + taskCode);
		}

		if (orderDetailDeliveryDataDomain.getExecTimes() >= orderDetailDeliveryDataDomain.getMaxExecTimes()) {
			throw new RuntimeException("作业执行次数超过阈值,不能继续操作，taskCode=" + taskCode);
		}

		OrderDetailDeliveryDataDomain updateDomain = new OrderDetailDeliveryDataDomain();
		updateDomain.setLastExecBeginTime(new Date());
		updateDomain.setId(orderDetailDeliveryDataDomain.getId());
		updateDomain.setUpdateUserCode(AuthThreadLocal.getUserCode());
		updateDomain.setExecTimes(orderDetailDeliveryDataDomain.getExecTimes() + 1);
		updateDomain.setLastExecComputer(SystemTools.hostname());
		updateDomain.setLastExecProcessId(SystemTools.processId());

		try {
			BsiOrder bsiOrder = new BsiOrder();
			bsiOrder.setBirthday(orderDetailDeliveryDataDomain.getBenefBirthday());

			String certiTypeStr = orderDetailDeliveryDataDomain.getBenefIdTyp();
			int certiType = certiTypeStr == null ? null : Integer.parseInt(certiTypeStr);
			bsiOrder.setCertiType(certiType);
			bsiOrder.setGoodId(Integer.parseInt(orderDetailDeliveryDataDomain.getOuterPhoneModelId()));
			bsiOrder.setGoodsSerialNo(orderDetailDeliveryDataDomain.getImei());
			bsiOrder.setIdCard(orderDetailDeliveryDataDomain.getBenefIdNumber());
			bsiOrder.setMobile(Long.parseLong(orderDetailDeliveryDataDomain.getBenefMobileNumber()));
			bsiOrder.setOutOrderID(orderDetailDeliveryDataDomain.getTaskCode());
			bsiOrder.setProductId(Integer.parseInt(orderDetailDeliveryDataDomain.getOuterProductId()));

			String sexStr = orderDetailDeliveryDataDomain.getBenefSex();
			int sex = sexStr == null ? null : Integer.parseInt(sexStr);
			bsiOrder.setSex(sex);
			bsiOrder.setUsername(orderDetailDeliveryDataDomain.getBenefName());

			CreateBsiOrderResponse createBsiOrderResponse = bsiOuterService.createBsiOrder(bsiOrder);

			updateDomain.setUpdateDate(new Date());
			updateDomain.setLastExecRtnCode(createBsiOrderResponse.getRtnCode());
			updateDomain.setLastExecRtnMsg(createBsiOrderResponse.getRtnMsg());
			updateDomain.setDeliveryStatusMemo(createBsiOrderResponse.getRtnMsg());

			if (createBsiOrderResponse.isSuccess()) {
				updateDomain.setTaskStatus(ConstOrder.TaskStatus.SUCCESS_TASK);
				updateDomain.setOuterOrderCode(createBsiOrderResponse.getOuterOrderCode());
				updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.SUCCESS);
			}else{
				updateDomain.setTaskStatus(ConstOrder.TaskStatus.FAILURE_TASK);
				updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.FAILURE);				
			}
		} catch (Exception ex) {
			logger.error("Failed to execute BsiTaskService.buyBsi(),taskCode="+taskCode,ex);
			updateDomain.setTaskStatus(ConstOrder.TaskStatus.EXECUTE_FAILED);
			updateDomain.setLastExecRtnCode(ex.getClass().getSimpleName());
			updateDomain.setLastExecRtnMsg(ex.getMessage());
			if (updateDomain.getExecTimes() >= orderDetailDeliveryDataDomain.getMaxExecTimes()){
				updateDomain.setTaskStatus(ConstOrder.TaskStatus.FAILURE_TASK);
				updateDomain.setDeliveryStatus(ConstOrder.DeliveryStatus.FAILURE);
				updateDomain.setTaskMemo("超过最大允许执行次数");
			}else{
				updateDomain.setScheduleExecDate(computeScheduleExecDate(updateDomain.getExecTimes()));
			}
		}finally{
			updateDomain.setUpdateDate(new Date());
			updateDomain.setLastExecEndTime(new Date());
			orderDetailDeliveryDataMapper.updateWithoutNull(updateDomain);
		}
	}

	private Date computeScheduleExecDate(int execTimes){
		Date now = new Date();
		if (execTimes == 0){
			return now;
		}else if (execTimes == 1){
			return DateTools.addMinutes(now, 15);
		}else if (execTimes == 2){
			return DateTools.addMinutes(now, 60);
		}else if (execTimes == 3){
			return DateTools.addMinutes(now, 120);
		}else if (execTimes == 4){
			return DateTools.addMinutes(now, 60*12);
		}else if (execTimes == 5){
			return DateTools.addMinutes(now, 60*24);
		}else if (execTimes == 6){
			return DateTools.addMinutes(now, 60*48);
		}else{
			return DateTools.addMinutes(now, 60*48);
		}
	}
}
