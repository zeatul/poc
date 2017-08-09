package com.hawk.ecom.trans.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * table = t_tra_order_detail desc = 订单明细
 * 
 * @author Gen
 */
public interface OrderDetailExMapper {

	public int updateOrderDetailStatus(@Param("orderDetailId") Integer orderDetailId, @Param("orderDetailStatus") Integer orderDetailStatus);

	/**
	 * 查询所有的交付已经处理完成,超过一定时间，但是自身状态还是处理中的订单明细
	 * @param orderDetailStatus
	 * @param deliveryStatusList
	 * @param threshold
	 * @param limit
	 * @return
	 */
	public List<Integer> queryUncheckedSuccessOrderDetail(@Param("orderDetailStatus") Integer orderDetailStatus,
			@Param("deliveryStatusList") List<Integer> deliveryStatusList,
			@Param("maxDate") Date threshold,
			@Param("limit") Integer limit);
	
	/**
	 * 查询存在交付失败,超过一定时间，但是自身状态还是处理中的订单明细
	 * @param orderDetailStatus
	 * @param deliveryStatusList
	 * @param threshold
	 * @param limit
	 * @return
	 */
	public List<Integer> queryUncheckedFailedOrderDetail(@Param("orderDetailStatus") Integer orderDetailStatus,
			@Param("deliveryStatusList") List<Integer> deliveryStatusList,
			@Param("maxDate") Date threshold,
			@Param("limit") Integer limit);
}