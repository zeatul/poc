package com.hawk.ecom.pub.web;

/**
 * 储存线程自有的用户认证变量
 * @author pzhang1
 *
 */
public class AuthThreadLocal {
	
	public static String getMobileNumber() {
		return threadLocalMobileNumber.get();
	}

	public static void setMobileNumber(String mobileNumber) {
		threadLocalMobileNumber.set(mobileNumber);
	}

	private static ThreadLocal<Long> threadLoaclUserId = new ThreadLocal<Long>();
	
	private static ThreadLocal<String> threadLocalToken = new ThreadLocal<String>();
	
	private static ThreadLocal<String> threadLocalMobileNumber = new ThreadLocal<String>();
	
	public static Long getUserId(){
		return threadLoaclUserId.get();
	}
	
	public static void setUserId(Long userId){
		threadLoaclUserId.set(userId);
	}
	
	public static String getToken(){
		return threadLocalToken.get();
	}
	
	public static void setToken(String token){
		threadLocalToken.set(token);
	}
	
	

}
