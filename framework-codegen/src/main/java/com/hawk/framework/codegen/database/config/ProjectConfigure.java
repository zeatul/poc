package com.hawk.framework.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hawk.framework.codegen.utils.ProjectTools;
import com.hawk.framework.utility.ClassPathTools;

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
	
	public static IProjectConfigure build(String packageName){
		
		ProjectConfigure projectConfigure = new ProjectConfigure();
		/**
		 * 读取配置文件
		 */
		Properties props = new Properties();
		String classPath = ClassPathTools.dotToAbsoluteClassPath(packageName)+ "/project.properties";
		InputStream in = ProjectConfigure.class.getResourceAsStream(classPath);
		if (in == null)
			throw new RuntimeException("Couldn't open stream = " + classPath);
		in = new BufferedInputStream(in);
		
		try {
			props.load(in);
			String projectName =props.getProperty("projectName");
			projectConfigure.setProjectName(projectName);
			projectConfigure.setRootPackage(props.getProperty("rootPackage"));
			projectConfigure.setSubPackage(props.getProperty("subPackage"));
			String projectRootDirectory = props.getProperty("projectRootDirectory");
			if (projectRootDirectory == null || projectRootDirectory.trim().length() == 0){
				projectRootDirectory = ProjectTools.computeProjectRootDirectory(projectName);
			}
				
			projectConfigure.setProjectRootDirectory(projectRootDirectory);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		return projectConfigure;
	}

	
}
