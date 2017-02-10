package com.hawk.framework.codegen.database.config;

public interface IProjectConfigure {
	
	public String getRootPackage();
	public String getSubPackage();
	/**
	 * 
	 * @return 项目名称，可以根据名称来计算projectRootDirectory,尽量使用projectName,而不要直接赋值ProjectRootDirectory
	 */
	public String getProjectName();
	
	/**
	 * 
	 * @return 返回项目所在的根目录，不包括src/main/java ，为空则根据projectName计算出来，否则用
	 */
	public String getProjectRootDirectory();
	
	/**
	 * 项目与code-gen的相对位置，0 表示平级
	 * @return
	 */
	public int getRelative();

}
