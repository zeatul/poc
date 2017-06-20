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

	private static ThreadLocal<Integer>   threadLoaclUserId = new ThreadLocal<Integer>  ();
	
	private static ThreadLocal<String> threadLoaclUserCode = new ThreadLocal<String>();
	
	private static ThreadLocal<String> threadLocalToken = new ThreadLocal<String>();
	
	private static ThreadLocal<String> threadLocalMobileNumber = new ThreadLocal<String>();
	
	private static ThreadLocal<String> threadLocalStoreCode = new ThreadLocal<String>();
	
	private static ThreadLocal<HttpRequestInfo> threadLocalHttpRequestInfo = new ThreadLocal<HttpRequestInfo>();
	
	public static void setStoreCode(String storeCode){
		threadLocalStoreCode.set(storeCode);
	}
	
	public static String getStoreCode(){
		return threadLocalStoreCode.get();
	}
	
	public static String getUserCode(){
		return threadLoaclUserCode.get();
	}
	
	public static void setUserCode(String userCode){
		threadLoaclUserCode.set(userCode);
	}
	
	public static Integer getUserId(){
		return threadLoaclUserId.get();
	}
	
	public static void setUserId(Integer  userId){
		threadLoaclUserId.set(userId);
	}
	
	public static String getToken(){
		return threadLocalToken.get();
	}
	
	public static void setToken(String token){
		threadLocalToken.set(token);
	}
	
	public static HttpRequestInfo getHttpRequestInfo(){
		return threadLocalHttpRequestInfo.get();
	}
	
	public static void setHttpRequestInfo(HttpRequestInfo httpRequestInfo){
		threadLocalHttpRequestInfo.set(httpRequestInfo);
	}

}
