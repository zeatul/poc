package com.hawk.ecom.svp.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.exception.OuterCallException;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.ecom.svp.service.BsiOuterService;
import com.hawk.ecom.svp.service.BsiOuterService.Order;
import com.hawk.ecom.svp.utils.ScheduleTools;
import com.hawk.framework.pub.cache.RedisCacheServiceImpl;
import com.hawk.framework.pub.spring.FrameworkContext;
import com.hawk.framework.utility.tools.StringTools;
import com.hawk.framework.utility.tools.SystemTools;

/**
 * 创建碎屏险外部订单job
 * 
 * @author Administrator
 *
 */
public class BsiOuterCreateOrderJob implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BsiOrderDetailDomain bsiOrderDetailDomain;

	public BsiOuterCreateOrderJob(BsiOrderDetailDomain bsiOrderDetailDomain) {
		this.bsiOrderDetailDomain = bsiOrderDetailDomain;
	}
	
	private String taskCode ;
	public BsiOuterCreateOrderJob(String taskCode){
		this.taskCode = taskCode;
	}

	private final static BsiOuterService bsiOuterService = FrameworkContext.getBean(BsiOuterService.class);
	private final static BsiOrderDetailService bsiOrderDetailService = FrameworkContext.getBean(BsiOrderDetailService.class);
	private final static TaskPool taskPool = FrameworkContext.getBean(TaskPool.class);
	private final static RedisCacheServiceImpl cacheService = FrameworkContext.getBean(RedisCacheServiceImpl.class);

	private static Order buildOrder(BsiOrderDetailDomain bsiOrderDetailDomain) {
		Order order = new Order();
		order.setBirthday(bsiOrderDetailDomain.getBsiBenefBirthday());
		order.setCertiType(bsiOrderDetailDomain.getBsiBenefIdTyp());
		order.setGoodId(bsiOrderDetailDomain.getBsiPhoneModelId());
		order.setGoodsSerialNo(bsiOrderDetailDomain.getImei());
		order.setIdCard(bsiOrderDetailDomain.getBsiBenefIdNumber());
		order.setMobile(Long.parseLong(bsiOrderDetailDomain.getBsiBenefMobileNumber()));
		order.setOutOrderID(bsiOrderDetailDomain.getBsiTaskCode());
		order.setProductId(bsiOrderDetailDomain.getBsiProductId());
		order.setSex(bsiOrderDetailDomain.getBsiBenefSex());
		order.setUsername(bsiOrderDetailDomain.getBsiBenefName());

		return order;
	}

	@Override
	public void run() {
		if (bsiOrderDetailDomain == null){
			logger.info("Start BsiOuterCreateOrderJob,taskCode={}",taskCode);
			bsiOrderDetailDomain = bsiOrderDetailService.loadByTaskCode(taskCode);
			if (bsiOrderDetailDomain == null){
				logger.error("Couldn't find bsiOrderDetailDomain,taskCode={}",taskCode);
			}
		}else{
			taskCode = bsiOrderDetailDomain.getBsiTaskCode();
			logger.info("Start BsiOuterCreateOrderJob ,  bsiOrderDetailDomain.taskCode={}" ,taskCode);
		}
		int bsiTaskStatus = bsiOrderDetailDomain.getBsiTaskStatus();
		if (bsiTaskStatus >= ConstBsiTaskStatus.COMPLETE_FAILED  ){
			logger.error("bsiOrderDetailDomain is in closed status, bsiTaskStatus = {}",bsiTaskStatus);
			return ;
		}

		/**
		 * TODO:增加redis缓存锁
		 */
		String key = "BsiOuterCreateOrderJob-" + taskCode;
		if (!cacheService.setnx(key, taskCode, 300-5)){
			logger.error("Failed to get the redis lock,taskCode={}",taskCode);
			return ;
		}
		

		Order order = buildOrder(bsiOrderDetailDomain);
		int execTimes = bsiOrderDetailDomain.getExecTimes();
		
		bsiOrderDetailDomain.setCurrentExecComputer(SystemTools.hostname());
		bsiOrderDetailDomain.setCurrentExecProcessId(SystemTools.processId());
		bsiOrderDetailDomain.setCurrentExecStartDate(new Date());

		try {
			bsiOrderDetailDomain.setUpdateDate(new Date());
			execTimes = execTimes +1;
			bsiOrderDetailDomain.setExecTimes(execTimes);

			/**
			 * TODO:用乐观锁卡住只能有一个执行
			 */

			bsiOrderDetailDomain.setLastExecDate(new Date());
			String bsiInsuranceCode = bsiOuterService.outCreateOrder(order);
			bsiOrderDetailDomain.setBsiInsuranceCode(bsiInsuranceCode);
			bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_SUCCESS);
		} catch (OuterCallException e) {
			logger.error("BsiOuterCreateOrderJob meet error",e);
			bsiOrderDetailDomain.setLastExecErrCode(new Integer(e.getCode()).toString());
			bsiOrderDetailDomain.setLastExecErrMsg(e.getMessage());
			bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_FAILED);
		} catch (Exception e) {
			logger.error("BsiOuterCreateOrderJob meet error",e);
			// 大于执行次数，更新代金券状态(添加失败原因) ，更新订单状态
			if (execTimes >= bsiOrderDetailDomain.getMaxExecTimes()) {
				bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.COMPLETE_FAILED);
				bsiOrderDetailDomain.setLastExecErrCode("overtimes");
				bsiOrderDetailDomain.setLastExecErrMsg("达到最大执行次数;"+e.getClass().getName()+";"+e.getMessage());
			} else {
				bsiOrderDetailDomain.setBsiTaskStatus(ConstBsiTaskStatus.EXEC_FAILED);
				bsiOrderDetailDomain.setLastExecErrCode(e.getClass().getName());
				bsiOrderDetailDomain.setLastExecErrMsg(e.getMessage());
				// 设置下次执行时间 scheduleTime
				Date scheduleExecDate = ScheduleTools.computeScheduleDate(execTimes);

				bsiOrderDetailDomain.setScheduleExecDate(scheduleExecDate);
			}

			
		} finally {
			/**
			 * 更新订单明细，更新订单 更新 代金券状态
			 */
			bsiOrderDetailService.update(bsiOrderDetailDomain);

			if (bsiOrderDetailDomain.getBsiTaskStatus() >= ConstBsiTaskStatus.COMPLETE_FAILED) {
				String bsiCashCouponCode = bsiOrderDetailDomain.getBsiCashCouponCode();
				if (StringTools.isNotNullOrEmpty(bsiCashCouponCode)) {
					BsiCashCouponSubJob bsiCashCouponSubJob = new BsiCashCouponSubJob(bsiOrderDetailDomain);
					taskPool.submit(bsiCashCouponSubJob);
				}

				SvpBsiOrderSubJob svpOrderSubJob = new SvpBsiOrderSubJob(bsiOrderDetailDomain.getOrderCode());
				taskPool.submit(svpOrderSubJob);
				
			}

			// dispatchServlet 拦截器 ,前后执行 ,mybatis cursor, spring batch , quartz

		}
	}

}
