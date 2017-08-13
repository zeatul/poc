package com.hawk.ecom.trans.persist.mapperex;

import java.util.Date;
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
	 * 查询已经支付，但是从未执行过的充值交付数据
	 * @param taskStatus
	 * @param orderStatus
	 * @return
	 */
	public List<OrderDetailDeliveryDataExDomain> loadOrderDeliveryDataForCharge(@Param("deliveryType") Integer deliveryType, 
			@Param("taskStatus") Integer taskStatus,@Param("orderStatus") Integer orderStatus
			,@Param("limit") Integer limit);

	/**
	 * 查询已经支付，但是还未成功购买碎屏险的交付数据
	 * @param deliveryType
	 * @param taskStatusList
	 * @param orderStatus
	 * @return
	 */
	public List<OrderDetailDeliveryDataExDomain> loadOrderDeliveryDataForBsi(@Param("deliveryType") Integer deliveryType, 
			@Param("taskStatusList") List<Integer> taskStatusList,@Param("orderStatus") Integer orderStatus
			,@Param("limit") Integer limit);
	
	/**
	 * 查询超时未获得充值结果的交付数据
	 * @param deliveryType
	 * @param maxDate
	 * @param deliveryStatus
	 * @return
	 */
	public List<Integer> loadOrderDeliveryDataForCheckChargeResult(@Param("deliveryType") Integer deliveryType, @Param("maxDate") Date maxDate , @Param("deliveryStatus") Integer deliveryStatus);

}
