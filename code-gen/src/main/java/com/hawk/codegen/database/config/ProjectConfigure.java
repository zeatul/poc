package com.hawk.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectConfigure implements IProjectConfigure {
	
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
	
	
	private ProjectConfigure(){
		
	}
	
	public static IProjectConfigure build(){
		
		ProjectConfigure projectConfigure = new ProjectConfigure();
		/**
		 * 读取配置文件
		 */
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(DatabaseConfigure.class.getResourceAsStream("project.properties"));
		
		try {
			props.load(in);
			projectConfigure.setProjectName(props.getProperty("projectName"));
			projectConfigure.setRootPackage(props.getProperty("rootPackage"));
			projectConfigure.setSubPackage(props.getProperty("subPackage"));
			projectConfigure.setProjectRootDirectory(props.getProperty("projectRootDirectory"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		return projectConfigure;
	}

	
}
