package com.hawk.framework.utility.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {
	
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	public final static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String DATETIME_SSS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
	
	private static ThreadLocal<SimpleDateFormat> threadLocalSimpleDateFormat = new ThreadLocal<SimpleDateFormat>(){
		
		protected SimpleDateFormat initialValue() {return new SimpleDateFormat();};
	};
	
	/**
	 * 将日期转化为指定格式字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convert(Date date, String pattern){
		SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 将指定格式字符串转化为日期
	 * @param dateStr
	 * @param pattern
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
			simpleDateFormat.applyPattern(pattern);
			return simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
