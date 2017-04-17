package com.hawk.ecom.svp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.persist.domain.OrderDomain;
import com.hawk.ecom.svp.persist.mapper.OrderMapper;

@Service
public class OrderService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrderMapper orderMapper;
	
	public OrderDomain loadById(Long id){
		if (id == null){
			logger.error("id is null");
			return null;
		}
		
		return orderMapper.load(id);
	}
	
	public int update(OrderDomain orderDomain){
		if (orderDomain == null){
			logger.error("orderDomain is null");
			return 0;
		}
		
		return orderMapper.update(orderDomain);
	}

}
