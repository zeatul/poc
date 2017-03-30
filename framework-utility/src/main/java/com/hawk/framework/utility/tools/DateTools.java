package com.hawk.framework.utility.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
	
	/**
	 * 日期所在月份的第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date){
		SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		simpleDateFormat.applyPattern(DATE_PATTERN);
		String strFirstDate = simpleDateFormat.format(gc.getTime());
		Date firstDate = null;
		try {
			firstDate = simpleDateFormat.parse(strFirstDate);
		} catch (ParseException e) {
			
		}
		return firstDate;		
		
	}
	
	/**
	 * 日期所在月份的最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date){
		SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.add(Calendar.DATE , -1);
		simpleDateFormat.applyPattern(DATE_PATTERN);
		String strFirstDate = simpleDateFormat.format(gc.getTime());
		Date firstDate = null;
		try {
			firstDate = simpleDateFormat.parse(strFirstDate);
		} catch (ParseException e) {
			
		}
		return firstDate;
	}
	
	/**
	 * 日期所在下个月份的第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfNextMonth(Date date){
		SimpleDateFormat simpleDateFormat = threadLocalSimpleDateFormat.get();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, 1);
		gc.set(Calendar.DAY_OF_MONTH, 1);
		simpleDateFormat.applyPattern(DATE_PATTERN);
		String strFirstDate = simpleDateFormat.format(gc.getTime());
		Date firstDate = null;
		try {
			firstDate = simpleDateFormat.parse(strFirstDate);
		} catch (ParseException e) {
			
		}
		return firstDate;
	}
	
	/**
	 * 返回指定时间加上指定分钟数
	 * @param date
	 * @param diff
	 * @return
	 */
	public static Date addMinutes(Date date, int diff){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, diff);
		return c.getTime();
	}
	
	/**
	 * 返回指定的时间加上月份数
	 * @param date
	 * @param diff
	 * @return
	 */
	public static Date addMonth(Date date,int diff){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, diff);
		return c.getTime();
	}
	
	public static void main(String[] args){
		System.out.println(firstDayOfMonth(new Date()));
		System.out.println(firstDayOfNextMonth(new Date()));
	}
}
