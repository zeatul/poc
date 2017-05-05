package com.hawk.framework.utility.tools;

public class RegexTools {
	
	public final static String MOBILE_NUMBER_PATTERN = "1[0-9]{10}";
	
	public final static String LOGIN_PWD_PATTERN = "([!@#$*]|[0-9]|[A-Z]|[a-z]){6,20}";
	
	
	public static void main(String[] args){
		System.out.println("!131111".matches(LOGIN_PWD_PATTERN));
	}
	


}
