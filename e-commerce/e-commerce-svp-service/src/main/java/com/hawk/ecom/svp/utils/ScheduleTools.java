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
			 * 第2次，15分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 15);
		}else if (execTimes == 3){
			/**
			 * 第3次，60分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 60);
		}else if (execTimes == 4){
			/**
			 * 第4次，720分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 360);
		}else if (execTimes == 5){
			/**
			 * 第5次，1440分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 1440);
		}else if (execTimes == 6){
			/**
			 * 第6次，1440分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 1440);
		}
		else if (execTimes == 7){
			/**
			 * 第7次，1440分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 1440);
		}
		else{
			/**
			 * 第8次，1440分钟
			 */
			scheduleExecDate = DateTools.addMinutes(scheduleExecDate, 1440);
		}
		
		return scheduleExecDate;
	}

}
