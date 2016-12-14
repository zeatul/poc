package com.hawk.utility;

public class StringTools {
	
	private static ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>(){
		
		protected StringBuilder initialValue() {return new StringBuilder();};
	};
	
	public static  StringBuilder getThreadSafeStringBuilder(){
		StringBuilder sb = threadLocalStringBuilder.get();
		sb.delete(0, sb.length());
		return sb;
	}
	
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
