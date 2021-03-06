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
import com.hawk.framework.pub.cache.RedisCacheServiceImpl;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.SystemTools;


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
	private final static RedisCacheServiceImpl cacheService = FrameworkContext.getBean(RedisCacheServiceImpl.class);
	
	private final static TaskPool taskPool = FrameworkContext.getBean(TaskPool.class);
	
	public MobileUnicomChargeJob(String chargeTaskCode) {
		this.chargeTaskCode = chargeTaskCode;
	}

	@Override
	public void run() {
		MobileDataOrderDetailDomain mobileDataOrderDetailDomain = mobileDataOrderDetailService.loadByTaskCode(chargeTaskCode);
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
			execTimes = execTimes +1;
			mobileDataOrderDetailDomain.setExecTimes(execTimes);
			mobileDataOrderDetailDomain.setCurrentExecStartDate(new Date());
			
			/**
			 * TODO：用乐观锁卡住只能执行一个
			 */
			String key = "MobileUnicomChargeJob-" + chargeTaskCode;
			if (!cacheService.setnx(key, chargeTaskCode, 300-5)){
				logger.error("Failed to get the redis lock");
				return ;
			}
			
			mobileDataOrderDetailDomain.setLastExecDate(new Date());
			
			mobileDataOrderDetailDomain.setCurrentExecComputer(SystemTools.hostname());
			mobileDataOrderDetailDomain.setCurrentExecProcessId(SystemTools.processId());
			
			unicomService.chargeVirtualMobileData(chargeTaskCode, mobileDataOrderDetailDomain.getChargeMobileNumber(), mobileDataOrderDetailDomain.getChargeDataSize());
		
			mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.COMPLETE_SUCCESS);
		
		}catch (OuterCallException  e){
			logger.error("MobileUnicomChargeJob meet error",e);
			mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.COMPLETE_FAILED);
			mobileDataOrderDetailDomain.setLastExecErrCode(new Integer(e.getCode()).toString());
			mobileDataOrderDetailDomain.setLastExecErrMsg(e.getMessage());
		}
		catch (Exception e) {
			logger.error("MobileUnicomChargeJob meet error",e);
			if (execTimes >= mobileDataOrderDetailDomain.getMaxExecTimes()) {
				mobileDataOrderDetailDomain.setChargeStatus(ConstChargeStatus.COMPLETE_FAILED);
				mobileDataOrderDetailDomain.setLastExecErrCode("overtimes");
				mobileDataOrderDetailDomain.setLastExecErrMsg("达到最大执行次数;"+e.getClass().getName()+";"+e.getMessage());
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
				MobileDataChargeOrderSubJob mobileDataChargeOrderSubJob = new MobileDataChargeOrderSubJob(mobileDataOrderDetailDomain.getOrderCode());
				taskPool.submit(mobileDataChargeOrderSubJob);
			}
		}
	}

}
