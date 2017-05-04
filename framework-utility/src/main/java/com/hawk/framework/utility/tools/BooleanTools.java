package com.hawk.framework.utility.tools;

public class BooleanTools {

	public static boolean parse(Integer v) {
		if (v == null)
			return false;
		if (v <= 0)
			return false;
		else
			return true;
	}

	public static boolean parse(String str) {
		if (StringTools.isNullOrEmpty(str))
			return false;
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
	
	public static int convet(String str){
		if (str.trim().equalsIgnoreCase("yes"))
			return 1;
		if (str.trim().equalsIgnoreCase("no"))
			return 0;
		if (str.trim().equalsIgnoreCase("true"))
			return 1;
		if (str.trim().equalsIgnoreCase("false"))
			return 0;
		throw new RuntimeException("illegal argument "+str);
	}

}
