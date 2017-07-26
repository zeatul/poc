package com.hawk.ecom.trans.persist.mapperex;

import java.util.List;
import java.util.Map;
import com.hawk.ecom.trans.persist.domain.OrderDetailDomain;
import org.apache.ibatis.annotations.Param;

/**
 * table = t_tra_order_detail desc = 订单明细
 * 
 * @author Gen
 */
public interface OrderDetailExMapper {

	public int updateOrderDetailStatus(@Param("orderDetailId") Integer orderDetailId, @Param("orderDetailStatus") Integer orderDetailStatus);

}