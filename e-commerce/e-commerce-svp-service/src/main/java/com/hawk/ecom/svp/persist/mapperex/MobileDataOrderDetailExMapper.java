package com.hawk.ecom.svp.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MobileDataOrderDetailExMapper {
	public List<String> taskCodeForJob(@Param("chargeStatus") Integer chargeStatus , @Param("scheduleExecDate") Date scheduleExecDate );
}
