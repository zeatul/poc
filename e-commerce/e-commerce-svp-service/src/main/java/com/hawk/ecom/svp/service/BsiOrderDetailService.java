package com.hawk.ecom.svp.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.BsiOrderDetailDomain;
import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.BsiOrderDetailMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class BsiOrderDetailService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BsiOrderDetailMapper bsiOrderDetailMapper;
	
	@Autowired
	private OrderService orderService;
	
	
	
	public int update(BsiOrderDetailDomain bsiOrderDetailDomain){
		
		if (bsiOrderDetailDomain == null){
			logger.error("The bsiOrderDetailDomain is null");
			return 0;
		}
		
		return bsiOrderDetailMapper.update(bsiOrderDetailDomain);
		
	}
	
	public List<BsiOrderDetailDomain> loadByOrderId(Long orderId){
		if (orderId == null){
			logger.error("The orderId is null");
			return Collections.emptyList();
		}
		
		MybatisParam params = new MybatisParam().put("orderId", orderId);
		return bsiOrderDetailMapper.loadDynamic(params);
	}
	
	public List<BsiOrderDetailDomain> loadByOrderCode(String orderCode){
		if (StringTools.isNullOrEmpty(orderCode)){
			logger.error("The orderCode is null");
			return Collections.emptyList();
		}
		OrderDomain orderDomain = orderService.loadByCode(orderCode);
		if (orderDomain == null){
			logger.error("Couldn't find the order with orderCode={}",orderCode);
			return Collections.emptyList();
		}
		
		
		MybatisParam params = new MybatisParam().put("orderId", orderDomain.getId());
		return bsiOrderDetailMapper.loadDynamic(params);
	}
}
