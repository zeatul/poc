package com.hawk.ecom.svp.utils;

import java.util.Date;

import com.hawk.framework.utility.tools.DateTools;

public class ScheduleTools {
	
	public static Date computeScheduleDate(int execTimes){
		Date scheduleExecDate = new Date();
		if (execTimes == 1){
			/**
			 * 第一次，5分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 5);
		}else if (execTimes == 2){
			/**
			 * 第2次，30分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 30);
		}else if (execTimes == 3){
			/**
			 * 第3次，120分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 120);
		}else if (execTimes == 4){
			/**
			 * 第4次，720分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 720);
		}else{
			/**
			 * 第5次，1440分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 1440);
		}
		
		return scheduleExecDate;
	}

}
