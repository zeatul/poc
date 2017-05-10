package com.hawk.ecom.svp.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BsiOrderDetailExMapper {

	public List<String> taskCodeForJob(@Param("bsiTaskStatus") Integer bsiTaskStatus , @Param("scheduleExecDate") Date scheduleExecDate);
}
