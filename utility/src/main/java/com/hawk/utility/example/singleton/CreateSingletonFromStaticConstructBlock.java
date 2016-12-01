package com.hawk.utility.example.singleton;

/**
 * 使用静态构造函数来实现单例
 * @author pzhang1
 *
 */
public class CreateSingletonFromStaticConstructBlock {
	
	private CreateSingletonFromStaticConstructBlock(){
		
	}
	
	private static CreateSingletonFromStaticConstructBlock instance = null;
	
	static {
		instance = new CreateSingletonFromStaticConstructBlock();
	}
	
	public static CreateSingletonFromStaticConstructBlock getInstance(){
		return instance;
	}

}
