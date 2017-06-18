package com.hawk.ecom.svp.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.constant.ConstBsiTaskStatus;
import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.ecom.svp.persist.mapperex.BsiOrderDetailExMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiOrderDetailService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BsiOrderDetailMapper bsiOrderDetailMapper;
	
	@Autowired
	private BsiOrderDetailExMapper bsiOrderDetailExMapper;
	
	
	
	public BsiOrderDetailDomain loadById(Integer  id){
		return bsiOrderDetailMapper.load(id);
	}
	
	public List<String> taskCodeForJob(){
		/**
		 * 小于ConstBsiTaskStatus.COMPLETE_FAILED 的是 未执行的
		 */
		return bsiOrderDetailExMapper.taskCodeForJob(ConstBsiTaskStatus.COMPLETE_FAILED, new Date());
	}
	
	public BsiOrderDetailDomain loadByTaskCode(String taskCode){
		if (StringTools.isNullOrEmpty(taskCode)){
			logger.info("taskCode is empty");
			return null;
		}
		MybatisParam params = new MybatisParam().put("bsiTaskCode", taskCode);
		return MybatisTools.single(bsiOrderDetailMapper.loadDynamic(params));
	}
	
	/**
	 * 暂时假定一个订单只能有有一条明细
	 * @param orderCode
	 * @return
	 */
	public BsiOrderDetailDomain loadByOrderCode(String orderCode){ 
		if (StringTools.isNullOrEmpty(orderCode)){
			logger.info("orderCode is empty");
			return null;
		}
		MybatisParam params = new MybatisParam().put("orderCode", orderCode);
		return MybatisTools.single(bsiOrderDetailMapper.loadDynamic(params));
	}
	
	public int update(BsiOrderDetailDomain bsiOrderDetailDomain){
		
		if (bsiOrderDetailDomain == null){
			logger.error("The bsiOrderDetailDomain is null");
			return 0;
		}
		
		return bsiOrderDetailMapper.update(bsiOrderDetailDomain);
		
	}
	
	
	
//	public List<BsiOrderDetailDomain> loadByOrderCode(String orderCode){
//		if (StringTools.isNullOrEmpty(orderCode)){
//			logger.error("The orderCode is null");
//			return Collections.emptyList();
//		}
//		OrderDomain orderDomain = orderService.loadByCode(orderCode);
//		if (orderDomain == null){
//			logger.error("Couldn't find the order with orderCode={}",orderCode);
//			return Collections.emptyList();
//		}
//		
//		
//		MybatisParam params = new MybatisParam().put("orderId", orderDomain.getId());
//		return bsiOrderDetailMapper.loadDynamic(params);
//	}
}
