package com.hawk.ecom.svp.job;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hawk.ecom.pub.job.TaskPool;
import com.hawk.ecom.svp.service.BsiOrderDetailService;
import com.hawk.ecom.svp.service.MobileDataOrderDetailService;
import com.hawk.framework.utility.tools.DateTools;

/**
 * 扫描 订单表 ，寻找那些已付款 ，但是没有完成的虚拟订单,发起作业来处理
 * 
 * @author Administrator
 *
 */
@Service
public class SvpOrderMainJob {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TaskPool taskPool;

	@Autowired
	private BsiOrderDetailService bsiOrderDetailService;

	@Autowired
	private MobileDataOrderDetailService mobileDataOrderDetailService;

	@Scheduled(initialDelay = 1000 * 77, fixedDelay = 1000 * 60 * 5)
	public void execute() {
		logger.info("Start SvpOrderMainJob at " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN));

		/**
		 * 搜索待处理的碎屏险子job，要控制订单状态为已经支付
		 */

		List<String> taskCodeList = bsiOrderDetailService.taskCodeForJob();
		logger.info("Found bsi taskcodeList.size()={}", taskCodeList.size());
		for (String taskCode : taskCodeList) {
			BsiOuterCreateOrderJob job = new BsiOuterCreateOrderJob(taskCode);
			taskPool.execute(job);
		}

		/**
		 * 搜索待处理的虚拟流量充值子job
		 */
		List<String> chargeTaskCodeList = mobileDataOrderDetailService.taskCodeForJob();
		logger.info("Found mobile data taskcodeList.size()={}", taskCodeList.size());
		for (String taskCode : chargeTaskCodeList) {
			MobileUnicomChargeJob job = new MobileUnicomChargeJob(taskCode);
			taskPool.execute(job);
		}

		logger.info("Finish SvpOrderMainJob at " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN));
	}
}
