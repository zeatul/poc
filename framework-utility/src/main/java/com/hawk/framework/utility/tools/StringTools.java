package com.hawk.framework.utility.tools;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StringTools {
	
	private static ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>(){
		
		protected StringBuilder initialValue() {return new StringBuilder();};
	};
	
	/**
	 * 返回一个线程安全的StringBuilder
	 * @return
	 */
	public static  StringBuilder getThreadSafeStringBuilder(){
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
	public static boolean isNullOrEmpty(String str){
		if (str == null || str.trim().length() == 0)
			return true;
		return false;
	}
	
	/**
	 * 判断字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isNotNullOrEmpty(String str){
		return !isNullOrEmpty(str);
	}
	
	/**
	 * 比较字符串是否相等,并且兼容空值
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean compare(String str1,String str2){
		if (str1 == null){
			return str2 ==null;
		}else{
			return str1.equals(str2);
		}
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
	
	
	private static Random random = new Random();
	/**
	 * 产生随机的纯数字的字符串
	 * @param length 字符串长度
	 * @return
	 */
	public static String randomNumberString(int length){
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length ; i++){			
//			int t = ThreadLocalRandom.current().nextInt(10);	
			int t = random.nextInt(10);
			sb.append(t);
		}
		return sb.toString();
	}
}
