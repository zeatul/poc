package com.hawk.framework.codegen.utils;

public class BooleanTools {

	public static boolean parse(int v) {
		if (v <= 0)
			return false;
		else
			return true;
	}

	public static boolean parse(String str) {
		if (str.trim().equalsIgnoreCase("yes"))
			return true;
		if (str.trim().equalsIgnoreCase("no"))
			return false;
		if (str.trim().equalsIgnoreCase("true"))
			return true;
		if (str.trim().equalsIgnoreCase("false"))
			return true;
		throw new RuntimeException("illegal argument "+str);
	}
	
	public static int convert(boolean b){
		if (b)
			return 1;
		else
			return 0;		
	}
	
	public static String yesOrNo(boolean b){
		if (b)
			return "yes";
		else
			return "no";
	}

}
