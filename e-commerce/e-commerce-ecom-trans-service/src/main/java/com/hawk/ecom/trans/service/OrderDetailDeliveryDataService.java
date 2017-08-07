package com.hawk.ecom.trans.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.trans.exception.OrderDetailDeliveryDataNotFoundRuntimeException;
import com.hawk.ecom.trans.persist.domain.OrderDetailDeliveryDataDomain;
import com.hawk.ecom.trans.persist.mapper.OrderDetailDeliveryDataMapper;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class OrderDetailDeliveryDataService {
	
	@Autowired
	private OrderDetailDeliveryDataMapper orderDetailDeliveryDataMapper;
	
	public OrderDetailDeliveryDataDomain loadByTaskCode(String taskCode){
		OrderDetailDeliveryDataDomain orderDetailDeliveryDataDomain = null;
		if (StringTools.isNotNullOrEmpty(taskCode)){
			MybatisParam params = new MybatisParam().put("taskCode", taskCode);
			orderDetailDeliveryDataDomain = MybatisTools.single(orderDetailDeliveryDataMapper.loadDynamic(params));
		}
		if (orderDetailDeliveryDataDomain == null){
			throw new OrderDetailDeliveryDataNotFoundRuntimeException();
		}
		return orderDetailDeliveryDataDomain;
	}
	
	@Valid
	public List<OrderDetailDeliveryDataDomain> queryByOrderDetailId(@NotNull Integer orderDetailId){
		MybatisParam params = new MybatisParam().put("orderDetailId", orderDetailId);
		return orderDetailDeliveryDataMapper.loadDynamic(params);
	}
}
