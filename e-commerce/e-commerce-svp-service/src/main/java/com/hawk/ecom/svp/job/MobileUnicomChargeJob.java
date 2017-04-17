package com.hawk.ecom.svp.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.svp.constant.ConstChargeStatus;
import com.hawk.ecom.svp.exception.OuterCallException;
import com.hawk.ecom.svp.persist.domain.MobileDataOrderDetailDomain;
import com.hawk.ecom.svp.service.MobileDataOrderDetailService;
import com.hawk.ecom.svp.service.UnicomService;
import com.hawk.ecom.svp.utils.ScheduleTools;
import com.hawk.framework.pub.spring.FrameworkContext;


/**
 * 联通手机充值job
 * @author Administrator
 *
 */
public class MobileUnicomChargeJob implements Runnable{
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private String chargeTaskCode;
	
	private final static UnicomService unicomService = FrameworkContext.getBean(UnicomService.class);
	
	private final static MobileDataOrderDetailService mobileDataOrderDetailService = FrameworkContext.getBean(MobileDataOrderDetailService.class);

	private final static TaskPool taskPool = FrameworkContext.getBean(TaskPool.class);
	
	public MobileUnicomChargeJob(String chargeTaskCode) {
		this.chargeTaskCode = chargeTaskCode;
	}

	@Override
	public void run() {
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain = mobileDataOrderDetailService.loadByTaskId(chargeTaskCode);
		if (mobileDataOrderDetailDomain == null){
			logger.error("Couldn't find mobileDataOrderDetailDomain with chargeTaskCode = {} ",chargeTaskCode);
			return ;
		}
		
		int chargeStatus = mobileDataOrderDetailDomain.getChargeStatus();
		if (chargeStatus >= ConstChargeStatus.COMPLETE_FAILED){
			logger.error("mobileDataOrderDetailDomain is in closed status,chargeStatus={}",chargeStatus);
			return ;
		}
		
		int execTimes = mobileDataOrderDetailDomain.getExecTimes();
		
		try {
			mobileDataOrderDetailDomain.setUpdateDate(new Date());
			mobileDataOrderDetailDomain.setExecTimes(execTimes + 1);
			
			/**
			 * TODO：用乐观锁卡住只能执行一个
			 */
			
			mobileDataOrderDetailDomain.setLastExecDate(new Date());
			unicomService.chargeVirtualMobileData(chargeTaskCode, mobileDataOrderDetailDomain.getChargeMobileNumber(), mobileDataOrderDetailDomain.getChargeDataSize());
		
			mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.COMPLETE_SUCCESS);
		
		}catch (OuterCallException  e){
			logger.error("MobileUnicomChargeJob meet error",e);
			mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.EXEC_FAILED);
			mobileDataOrderDetailDomain.setLastExecErrCode(e.getCode());
			mobileDataOrderDetailDomain.setLastExecErrMsg(e.getMessage());
		}
		catch (Exception e) {
			logger.error("MobileUnicomChargeJob meet error",e);
			if (execTimes >= mobileDataOrderDetailDomain.getMaxExecTimes()) {
				mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.COMPLETE_FAILED);
				mobileDataOrderDetailDomain.setLastExecErrCode("overtimes");
				mobileDataOrderDetailDomain.setLastExecErrMsg("达到最大执行次数");
			} else {
				mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.EXEC_FAILED);
				mobileDataOrderDetailDomain.setLastExecErrCode(e.getClass().getName());
				mobileDataOrderDetailDomain.setLastExecErrMsg(e.getMessage());
				// 设置下次执行时间 scheduleTime
				Date scheduleExecDate = ScheduleTools.computeScheduleDate(execTimes);

				mobileDataOrderDetailDomain.setScheduleExecDate(scheduleExecDate);
			}
		}finally{
			mobileDataOrderDetailService.update(mobileDataOrderDetailDomain);
			
			/**
			 * 更新订单状态
			 */
			if (mobileDataOrderDetailDomain.getChargeStatus() >= ConstChargeStatus.COMPLETE_FAILED){
				MobileDataChargeOrderSubJob mobileDataChargeOrderSubJob = new MobileDataChargeOrderSubJob(mobileDataOrderDetailDomain.getOrderId());
				taskPool.submit(mobileDataChargeOrderSubJob);
			}
		}
	}

}
