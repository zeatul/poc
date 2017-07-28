package com.hawk.ecom.task.spring.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.hawk.ecom.pub.job.TaskPool;


@Configuration
@Import({})
@ComponentScan(basePackages = { "com.hawk.ecom.task.service" })
@EnableScheduling
public class EcomTaskRootConfig {
	
	
	@Bean
	public TaskPool taskPool(){
		int coreSize = 5;
		int maxSize = 100;
		int keepAliveSize = 3;
		int capacity = 6000;
		BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(capacity);
		TaskPool taskPool = new TaskPool(coreSize, maxSize, keepAliveSize, queue);
		return taskPool;
	}
}
