package com.hawk.ecom.picture.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hawk.ecom.picture.config.UploadFileConfigure;
import com.hawk.ecom.picture.controller.HomeController;

@Configuration
//@PropertySource({"classpath:/com/hawk/ecom/picture/env/ecom_picture.properties"})
@ComponentScan(basePackageClasses = { HomeController.class })
public class EcomPictureWebConfig extends WebMvcConfigurerAdapter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
//	@Bean
//	public UploadFileConfigure uploadFileConfigure(){
//		UploadFileConfigure uploadFileConfigure = new UploadFileConfigure();
//		
//		String trackers = env.getProperty("ecom.picture.trackers");		
//		logger.info("ecom.picture.trackers={}",trackers);		
//		List<String> trackerList = new ArrayList<String>();		
//		uploadFileConfigure.setTrackers(trackerList);		
//		String[] strs = trackers.split(";");
//		for (String str : strs){
//			trackerList.add(str);
//		}
//		
//		String location = env.getProperty("ecom.picture.location");
//		logger.info("ecom.picture.location={}",location);
//		
//		long maxFilesSize = env.getProperty("ecom.picture.maxFilesSize",Long.class);
//		logger.info("ecom.picture.maxFilesSize={}",maxFilesSize);
//		
//		long maxRequestSize = env.getProperty("ecom.picture.maxRequestSize",Long.class);
//		logger.info("ecom.picture.maxRequestSize={}",maxRequestSize);
//		
//		int fileSizeThreshold = env.getProperty("ecom.picture.fileSizeThreshold",Integer.class);
//		logger.info("ecom.picture.fileSizeThreshold={}",fileSizeThreshold);
//		
//		return uploadFileConfigure;
//	}
}
