package com.hawk.framework.codegen.utils;

import java.io.File;

public class ProjectTools {
	
	/**
	 * 清理目录 
	 * @param directory
	 * @param filter 当前只支持过滤结尾是filter的文件 ，符合该条件的文件才被删除
	 */
	public static void  clearDirectory(String directory,String filter) {
		File file = new File(directory);
		if (!file.exists()){
			throw new RuntimeException("the directory "+directory+ " doesn't exist");
		}
		if (file.isDirectory()){
			throw new RuntimeException("the directory "+directory+ " is not a directory");
		}
		if (file.isDirectory()) {
			String[] f = file.list();
			for (String str : f) {
				if (str.toLowerCase().endsWith(filter.toLowerCase())){
					File f1 = new File(file + File.separator + str);
					f1.delete();
				}
			}
		}

	}
	
	/**
	 * 根据项目名称计算出项目所在的目录，前提是code-gen 和 该项目 在同一位置
	 * @param projectName 项目名称
	 * @param relative 相对位置,传"0",表示和code-gen工程目录并列,直属于同一个父目录
	 * @return
	 */
	public static String computeProjectRootDirectory(String projectName,int relative) {
		

			String baseDir = System.getProperty("user.dir");
			System.out.println(baseDir);
			String[] strArray = baseDir.split("\\\\");
			String dirPath = strArray[0];
			for (int i = 1; i < strArray.length - 1; i++) {
				dirPath = dirPath + File.separator + strArray[i];
			}
			return dirPath + File.separator +projectName;
	}
	
	/**
	 * 计算出代码文件所在的目录
	 * @param baseDir 项目根目录
	 * @param packages 项目代码所在的package ，顺序必须严格按照实际的要求来，例如 rootPackage ，subPackage ，domain
	 * @return 返回的目录名称最后一位不是文件分隔符
	 */
	public static String computeProjectSourceDirectory(String baseDir,String... packages){
		return comuteDirectory(baseDir,"src"+File.separator+"main"+File.separator+"java",packages);
	}
	
	/**
	 * 计算出资源文件所在的目录
	 * @param baseDir 项目根目录
	 * @param packages 项目代码所在的package ，顺序必须严格按照实际的要求来，例如 rootPackage ，subPackage ，domain
	 * @return 返回的目录名称最后一位不是文件分隔符
	 */
	public static String computeProjectResourceDirectory(String baseDir,String... packages){
				
		return comuteDirectory(baseDir,"src"+File.separator+"main"+File.separator+"resources",packages);
	}
	
	/**
	 * 计算出实际目录 baseDir/subDir/package....
	 * @param baseDir 父目录
	 * @param subDir 子目录 例如  src/main/java,分割符要严格使用File.separator
	 * @param packages  项目代码所在的package ，顺序必须严格按照实际的要求来，例如 rootPackage ，subPackage ，domain
	 * @return 返回的目录名称最后一位不是文件分隔符
	 */
	public static String comuteDirectory(String baseDir,String subDir,String... packages){
		StringBuilder sb = new StringBuilder();
		if (baseDir.endsWith(File.separator)){
			baseDir = baseDir.substring(1);
		}
		sb.append(baseDir);
		
		
		if (subDir != null && subDir.trim().length() != 0){
			String spliter = File.separator;
			if (spliter.equals("\\")){
				spliter = "\\\\";
			}
				
			String[] strArray = subDir.split(spliter);
			for(String str : strArray){
				sb.append(File.separator).append(str);
			}
		}
		
		sb.append("src").append(File.separator);
		sb.append("main").append(File.separator);
		sb.append("resources");
		
		for (String packageName : packages){
			String[] strArray = packageName.split("\\.");
			for(String str : strArray){
				sb.append(File.separator).append(str);
			}
		}
		
		return sb.toString();
	}
}
