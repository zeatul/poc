package com.hawk.framework.utility.tools;

public class CamelNameTools {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String capFirst(String str){
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase())  ;
	}
	
	public static String uncapFirst(String str){
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase())  ;
	}
	
	/**
	 * 改成驼峰法表示的字符串
	 * @param str 用分隔符表示的要改成驼峰法的字符串
	 * @param spliter 分隔符
	 * @param start t_module_tablename : 此处=2 表示从 tablename开始生成驼峰法表示的名称
	 * @return
	 */
	public static String camelName(String srcStr,String spliter,int start,boolean capFirst){
		String className = "";
		String[] strArray = srcStr.split(spliter);
		for (int i=start ; i<strArray.length; i++){
			String str = strArray[i].toLowerCase();
			className = className +capFirst(str);
		}
		if (!capFirst){
			className = uncapFirst(className);
		}
		return className;
	}
	
	/**
	 * 首字母小写,下划线分割, 数据库字段转java字段名
	 * @param srcStr
	 * @return
	 */
	public static String camelName(String srcStr){
		return camelName(srcStr,"_",0,false);
	}

}
