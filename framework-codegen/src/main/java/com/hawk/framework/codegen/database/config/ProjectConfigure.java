package com.hawk.framework.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hawk.framework.codegen.utils.ProjectTools;
import com.hawk.framework.utility.ClassPathTools;

public class ProjectConfigure implements IProjectConfigure {
	
	public int getRelative() {
		return relative;
	}

	public void setRelative(int relative) {
		this.relative = relative;
	}

	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;
	}

	public String getProjectRootDirectory() {
		return projectRootDirectory;
	}

	public void setProjectRootDirectory(String projectRootDirectory) {
		this.projectRootDirectory = projectRootDirectory;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRootPackage() {
		return rootPackage;
	}

	public void setRootPackage(String rootPackage) {
		this.rootPackage = rootPackage;
	}

	public String getSubPackage() {
		return subPackage;
	}


	

	private String projectName;
	private String rootPackage;
	private String subPackage;
	private String projectRootDirectory;
	private int relative ;
	
	
	private ProjectConfigure(){
		
	}
	
	public static IProjectConfigure build(String configFileClassPath){
		
		ProjectConfigure projectConfigure = new ProjectConfigure();
		/**
		 * 读取配置文件
		 */
		Properties props = new Properties();
		String classPath = ClassPathTools.dotToAbsoluteClassPath(configFileClassPath)+ "/project.properties";
		InputStream in = new BufferedInputStream(DatabaseConfigure.class.getResourceAsStream(classPath));
		
		try {
			props.load(in);
			String projectName =props.getProperty("projectName");
			projectConfigure.setProjectName(projectName);
			int relative = Integer.parseInt(props.getProperty("relative", "0"));
			projectConfigure.setRelative(relative);
			projectConfigure.setRootPackage(props.getProperty("rootPackage"));
			projectConfigure.setSubPackage(props.getProperty("subPackage"));
			String projectRootDirectory = props.getProperty("projectRootDirectory");
			if (projectRootDirectory == null || projectRootDirectory.trim().length() == 0){
				projectRootDirectory = ProjectTools.computeProjectRootDirectory(projectName, relative);
			}
				
			projectConfigure.setProjectRootDirectory(projectRootDirectory);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		return projectConfigure;
	}

	
}
