package com.hawk.ecom.trans.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface OrderExMapper {
	
	public List<Integer> queryUnpaidOvertimeOrder(@Param("orderStatus") Integer orderStatus, @Param("threashold") Date threashold ,@Param("limit") Integer limit);

}
