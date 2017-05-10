package com.hawk.ecom.svp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.OrderMapper;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class OrderService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderMapper orderMapper;
	
	public OrderDomain loadById(Long orderId){
		if (orderId == null){
			logger.error("orderId is null");
			return null;
		}
		
		return orderMapper.load(orderId);
	}
	
	public OrderDomain loadByOrderCode(String orderCode){
		if (StringTools.isNullOrEmpty(orderCode)){
			logger.error("orderCode is null");
			return null;
		}
		MybatisParam params = new MybatisParam().put("orderCode", orderCode);
		return MybatisTools.single(orderMapper.loadDynamic(params));
	}
	
	public int update(OrderDomain orderDomain){
		if (orderDomain == null){
			logger.error("orderDomain is null");
			return 0;
		}
		
		return orderMapper.update(orderDomain);
	}

}
