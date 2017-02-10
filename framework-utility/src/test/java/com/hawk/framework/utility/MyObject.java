package com.hawk.framework.utility;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyObject {

	/**
	 * 可以继承父线程的threadlocal变量
	 */
	private InheritableThreadLocal<String> it = new InheritableThreadLocal<String>();

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("I am hook!!!");
			}
		});
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("I am hook2!!!");
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;){
					System.out.println("I am working , you can't exit jvm");
					System.out.println("availableProcessors="+Runtime.getRuntime().availableProcessors());
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		System.exit(0);
		
	}
	
	public void test(){
		Executor executor = Executors.newCachedThreadPool();

		ExecutorService executorService =  Executors.newCachedThreadPool();

		ExecutorCompletionService<Object> executorCompletionService= new ExecutorCompletionService<>(executor);
		
		executorCompletionService.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		},null);
		
		Thread.interrupted();
		
		Thread.currentThread().interrupt();
		
		Future<Object> future;
		
		executorService.shutdownNow();
		System.exit(1);
	}

}
