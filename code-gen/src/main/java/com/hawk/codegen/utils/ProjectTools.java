package com.hawk.codegen.utils;

import java.io.File;

public class ProjectTools {
	
	/**
	 * 根据项目名称计算出项目所在的目录，前提是code-gen 和 该项目 在同一位置
	 * @param projectName 项目名称
	 * @param relative 相对位置,传"0",标识和code-gen工程目录并列,直属于同一个父目录
	 * @return
	 */
	public String computeProjectRootDirectory(String projectName,int relative) {
		if (projectDirPath == null) {

			String baseDir = System.getProperty("user.dir");
			System.out.println(baseDir);
			String[] strArray = baseDir.split("\\\\");
			String dirPath = strArray[0];
			for (int i = 1; i < strArray.length - 1; i++) {
				dirPath = dirPath + File.separator + strArray[i];
			}
			projectDirPath = dirPath + File.separator + this.projectName;
		}

		return projectDirPath;
	}
}
