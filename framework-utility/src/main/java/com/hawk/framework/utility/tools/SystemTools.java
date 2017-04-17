package com.hawk.framework.utility.tools;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemTools {
	
	/**
	 * 
	 * @return 获取当前环境的cpu数
	 */
	public int cpuAmount(){
		return Runtime.getRuntime().availableProcessors();
	}
	
	/**
	 * Registers a new virtual-machine shutdown hook
	 * 注册一个在jvm关闭时触发运行的钩子
	 */
	public void hookJvmShutdown(Runnable hook){
		Runtime.getRuntime().addShutdownHook(new Thread(hook));
	}
	
	/**
	 * 
	 * @return 操作系统名称
	 */
	public String operatingSystem(){
		return System.getProperty("os.name");
	}

	/**
	 * 
	 * @return 机器名
	 */
	public static String hostname(){
		String hostname = null;
		try {
			hostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			String host = e.getMessage();
			if (host != null){
				int colon = host.indexOf(':');
				if (colon > 0){
					hostname = host.substring(0, colon);
				}
			}
		}
		
		if (hostname == null)
			hostname = "UnknownHost";
		
		return hostname;
	}
	
	/**
	 * 
	 * @return 当前进程id
	 */
	public static String processId(){
		String name = ManagementFactory.getRuntimeMXBean().getName();
		String pid = name.split("@")[0];
		return pid;
	}
	
	public static void main(String[] args){
		System.out.println(processId());
	}
}
