package com.hawk.framework.utility;

public class ClassPathTools {
	
	/**
	 * 转换package格式路径为相对classpath格式,例如com.hawk.framework 转换成 com/hawk/framework
	 * @param s
	 * @return
	 */
	public static String dotToClassPath(String s) {
		return s.replace('.', '/');
	}
	
	/**
	 * 转换package格式路径为绝对对classpath格式 ,例如com.hawk.framework 转换成 /com/hawk/framework
	 * @param s
	 * @return
	 */
	public static String dotToAbsoluteClassPath(String s){
		return classPathtoAbsoluteClassPath(dotToClassPath(s));
	}

	/**
	 * 转换classpath格式(相对路径或绝对路径）为package路径格式 ,例如com/hawk/framework 或 /com/hawk/framework 都转换成 com.hawk.framework  
	 * @param s
	 * @return
	 */
	public static String classPathToDot(String s) {
		return s.replace('/', '.').replace('\\', '.');
	}

	/**
	 * 转换classpath相对路径为绝对路径,例如 com/hawk/framework转换成/com/hawk/framework
	 * @param s
	 * @return
	 */
	public static String classPathtoAbsoluteClassPath(String s) {
		if (!s.startsWith("/"))
			s = "/" + s;

		return s.replace('\\', '/');
	}

}
