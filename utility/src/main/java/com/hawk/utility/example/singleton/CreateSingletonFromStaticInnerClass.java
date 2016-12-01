package com.hawk.utility.example.singleton;

/**
 * 利用静态内部类来实现单例
 * @author pzhang1
 *
 */
public class CreateSingletonFromStaticInnerClass {
	
	private CreateSingletonFromStaticInnerClass(){
		
	}
	
	
	private static class SingletonHandler{
		public static CreateSingletonFromStaticInnerClass singleton = new CreateSingletonFromStaticInnerClass();
	}
	
	public static CreateSingletonFromStaticInnerClass getInstance(){
		return SingletonHandler.singleton;
	}

}
