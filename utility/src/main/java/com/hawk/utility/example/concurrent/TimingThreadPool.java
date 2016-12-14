package com.hawk.utility.example.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * java并发编程实践
 * 清单8.9：扩展线程池以提供日志和计时功能
 * @author pzhang1
 *
 */
public class TimingThreadPool extends ThreadPoolExecutor{
	
	public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		logger.info("Start");
		startTime.set(System.nanoTime());
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime-startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			logger.info("end");
		} finally{
			super.afterExecute(r, t);
		}
	}

	@Override
	protected void terminated() {
		try{
			/**
			 * 计算线程池的平均任务执行时间
			 */
			logger.info(""+totalTime.get()/numTasks.get());
		}finally{
			super.terminated();
		}
	}
}
