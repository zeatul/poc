package com.hawk.ecom.trans.persist.mapperex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.trans.persist.domainex.OrderDetailDeliveryDataExDomain;



public interface OrderDetailDeliveryDataExMapper {
	public int updateOrderDeliveryDataForNotification(Map<String, Object> params);
	
	/**
	 * 根据taskCode 查询指定任务
	 * @param taskCode
	 * @return
	 */
	public OrderDetailDeliveryDataExDomain loadOrderDeliveryDataByTaskCode(@Param("taskCode") String taskCode);
	
	/**
	 * 查询已经支付，但是从未执行过的作业
	 * @param taskStatus
	 * @param orderStatus
	 * @return
	 */
	public List<OrderDetailDeliveryDataExDomain> loadOrderDeliveryDataForCharge(@Param("deliveryType") Integer deliveryType, @Param("taskStatus") Integer taskStatus,@Param("orderStatus") Integer orderStatus);
}
