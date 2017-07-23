package com.hawk.ecom.trans.persist.mapperex;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;



public interface OrderDetailDeliveryDataExMapper {
	public int updateOrderDeliveryDataForNotification(Map<String, Object> params);
	
	public OrderDetailDeliveryDataExDomain loadOrderDeliveryDataByTaskCode(@Param("taskCode") String taskCode);
	
}
