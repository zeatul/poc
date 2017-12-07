package com.hawk.ecom.picture.spring.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.tobato.fastdfs.conn.ConnectionPoolConfig;
import com.github.tobato.fastdfs.conn.FdfsConnectionPool;
import com.github.tobato.fastdfs.conn.PooledConnectionFactory;
import com.github.tobato.fastdfs.conn.TrackerConnectionManager;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.picture.service" ,"com.github.tobato.fastdfs.service,com.github.tobato.fastdfs.domain"})
public class EcomPictureRootConfig {
	
	
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
	
	@Bean
	public TrackerConnectionManager fdfsTrackerConnectionManager(FdfsConnectionPool fdfsConnectionPool){
		
		List<String> trackerList = Arrays.asList("210.73.195.77:22122");
		
		TrackerConnectionManager trackerConnectionManager =new TrackerConnectionManager(fdfsConnectionPool);
		
		trackerConnectionManager.setTrackerList(trackerList);
		
		return trackerConnectionManager;
	}
	
}
