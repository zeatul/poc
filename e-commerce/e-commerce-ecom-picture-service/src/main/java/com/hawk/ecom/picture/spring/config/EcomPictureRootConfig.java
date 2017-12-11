package com.hawk.ecom.picture.spring.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.hawk.ecom.picture.config.UploadFileConfigure;

import com.github.tobato.fastdfs.conn.ConnectionPoolConfig;
import com.github.tobato.fastdfs.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.conn.TrackerConnectionManager;


@Configuration
@Import({})
@PropertySource({"classpath:/com/hawk/ecom/picture/env/ecom_picture.properties"})
@ComponentScan(basePackages = { "com.hawk.ecom.picture.service" ,"com.github.tobato.fastdfs.service,com.github.tobato.fastdfs.domain"})
public class EcomPictureRootConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public ConnectionPoolConfig fdfsConnectionPoolConfig(){
		return new ConnectionPoolConfig();
	}
	
	@Bean
	public PooledConnectionFactory fdfsPooledConnectionFactory(){
		return new PooledConnectionFactory();
	}

	@Bean
	public FdfsConnectionPool fdfsConnectionPool(ConnectionPoolConfig fdfsConnectionPoolConfig,PooledConnectionFactory fdfsPooledConnectionFactory){
		return new FdfsConnectionPool(fdfsPooledConnectionFactory,fdfsConnectionPoolConfig);
	}
	
	@Autowired
	private UploadFileConfigure uploadFileConfigure;
	
	@Bean
	public UploadFileConfigure uploadFileConfigure(){
		UploadFileConfigure uploadFileConfigure = new UploadFileConfigure();
		
		String trackers = env.getProperty("ecom.picture.trackers");		
		logger.info("ecom.picture.trackers={}",trackers);		
		List<String> trackerList = new ArrayList<String>();						
		String[] strs = trackers.split(";");
		for (String str : strs){
			trackerList.add(str);
		}
		uploadFileConfigure.setTrackers(trackerList);
		
//		String location = env.getProperty("ecom.picture.location");
//		logger.info("ecom.picture.location={}",location);
//		uploadFileConfigure.setLocation(location);
//		
//		long maxFilesSize = env.getProperty("ecom.picture.maxFilesSize",Long.class);
//		logger.info("ecom.picture.maxFilesSize={}",maxFilesSize);
//		uploadFileConfigure.setMaxFilesSize(maxFilesSize);
//		
//		long maxRequestSize = env.getProperty("ecom.picture.maxRequestSize",Long.class);
//		logger.info("ecom.picture.maxRequestSize={}",maxRequestSize);
//		uploadFileConfigure.setMaxRequestSize(maxRequestSize);
//		
//		int fileSizeThreshold = env.getProperty("ecom.picture.fileSizeThreshold",Integer.class);
//		logger.info("ecom.picture.fileSizeThreshold={}",fileSizeThreshold);
//		uploadFileConfigure.setFileSizeThreshold(fileSizeThreshold);
		
		return uploadFileConfigure;
	}
	
	@Bean
	public TrackerConnectionManager fdfsTrackerConnectionManager(FdfsConnectionPool fdfsConnectionPool){
		
		
		
		TrackerConnectionManager trackerConnectionManager =new TrackerConnectionManager(fdfsConnectionPool);
		
		trackerConnectionManager.setTrackerList(uploadFileConfigure.getTrackers());
		
		return trackerConnectionManager;
	}
	
}
