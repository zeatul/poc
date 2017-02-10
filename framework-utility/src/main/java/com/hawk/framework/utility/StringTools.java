package com.hawk.framework.utility;

import java.util.List;

public class StringTools {
	
	private static ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>(){
		
		protected StringBuilder initialValue() {return new StringBuilder();};
	};
	
	/**
	 * 返回一个线程安全的StringBuilder
	 * @return
	 */
	private static  StringBuilder getThreadSafeStringBuilder(){
		StringBuilder sb = threadLocalStringBuilder.get();
		sb.delete(0, sb.length());
		return sb;
	}
	
	/**
	 * 连接
	 * @param objects
	 * @return
	 */
	public static String concat(Object... objects){
		StringBuilder sb = getThreadSafeStringBuilder();
		for (Object o : objects){
			sb.append(o);
		}
		return sb.toString();
	}
	
	/**
	 * 连接
	 * @param objects
	 * @return
	 */
	public static String concat(List<?> objects){
		StringBuilder sb = getThreadSafeStringBuilder();
		for (Object o : objects){
			sb.append(o);
		}
		return sb.toString();
	}
	
	/**
	 * 连接,连接对象用symbol隔开
	 * @param symbol
	 * @param objects
	 * @return
	 */
	public static String concatWithSymbol(String symbol ,Object... objects){
		StringBuilder sb = getThreadSafeStringBuilder();
		for (Object o : objects){
			sb.append(o);
			sb.append(symbol);
		}
		return sb.substring(0,sb.length()-1);
	}
	
	/**
	 * 连接,连接对象用symbol隔开
	 * @param symbol
	 * @param objects
	 * @return
	 */
	public static String concatWithSymbol(String symbol ,List<?> objects){
		StringBuilder sb = getThreadSafeStringBuilder();
		for (Object o : objects){
			sb.append(o);
			sb.append(symbol);
		}
		return sb.substring(0,sb.length()-1);
	}
	
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public boolean isEmptyOrNull(String str){
		if (str == null || str.trim().length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public boolean isNotEmptyOrNull(String str){
		return !isEmptyOrNull(str);
	}
	
	
	
	/**
	 * 将long转换成36进制(0到9和a到z)的字符串
	 * @param x
	 * @return
	 */
	public static String to36Char(long x){
		StringBuilder sb = getThreadSafeStringBuilder();
		to36Char(x,sb);
		return sb.reverse().toString();
	}
	
	private static void to36Char(long x,StringBuilder sb){
		long l = x / 36;
		long r = x % 36;
		
		if (r>=0 && r<=9){
			sb.append(r);
		}else{
			char c = (char)((r-10)+65);
			sb.append(c);
		}
		
		if (l>0)
			to36Char(l,sb);
	}

}
