package com.hawk.ecom.pub.job;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 作业执行器
 * @author Administrator
 *
 */
public class TaskPool extends ThreadPoolExecutor{

	public TaskPool(int coreSize, int maxSize,int keepAliveSize,BlockingQueue<Runnable> queue){
		super(coreSize,maxSize,keepAliveSize,TimeUnit.SECONDS,queue);
		this.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	}

}
