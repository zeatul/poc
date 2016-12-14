package com.hawk.utility.example.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 打印出当前虚拟机的所有线程信息
 * @author pzhang1
 *
 */
public class MultiThread {

	public static void main(String[] args) {
		/**
		 * 获取java线程管理器MXBean
		 */
		ThreadMXBean threadMXBean =	ManagementFactory.getThreadMXBean();
		/**
		 * 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
		 */
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		/**
		 * 变量线程信息，仅打印线程ID和线程名称信息
		 */
		for (ThreadInfo threadInfo : threadInfos){
			System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}
		
		Thread.currentThread().interrupt();
		
		System.out.println("Interrupted="+Thread.currentThread().isInterrupted());
		
		System.out.println("Interrupted="+Thread.interrupted());
		
		System.out.println("Interrupted="+Thread.currentThread().isInterrupted());
	}

}
