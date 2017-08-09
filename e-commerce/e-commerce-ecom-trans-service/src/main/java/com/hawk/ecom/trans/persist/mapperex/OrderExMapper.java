package com.hawk.ecom.trans.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderExMapper {
	
	public List<Integer> queryUnpaidOvertimeOrder(@Param("orderStatus") Integer orderStatus, @Param("threshold") Date threshold ,@Param("limit") Integer limit);

	public List<Integer> queryUncheckedSuccessOrder(@Param("orderStatus") Integer orderStatus, @Param("maxDate") Date threshold ,
			@Param("orderDetailStatusList") List<Integer> orderDetailStatusList,@Param("limit") Integer limit);
	
	public List<Integer> queryUncheckedFailedOrder(@Param("orderStatus") Integer orderStatus, @Param("maxDate") Date threshold ,
			@Param("orderDetailStatusList") List<Integer> orderDetailStatusList,@Param("limit") Integer limit);
}
