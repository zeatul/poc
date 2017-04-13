package com.hawk.ecom.svp.spring.config;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hawk.ecom.pub.job.TaskPool;

@Configuration
public class SvpJobConfigure {
	
	@Bean
	public TaskPool taskPool(){
		int coreSize = 10;
		int maxSize = 20;
		int keepAliveSize = 3;
		int capacity = 40;
		BlockingQueue<Runnable> queue = new LinkedBlockingDeque<Runnable>(capacity);
		TaskPool taskPool = new TaskPool(coreSize, maxSize, keepAliveSize, queue);
		return taskPool;
	}

}
