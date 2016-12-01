package com.hawk.utility.example.singleton;

/**
 * 利用枚举成员只有一个实例的原理来实现单例
 * 
 * @author pzhang1
 *
 */
public class CreateSingletonFromEnum {

	public enum MyEnumSingleton {
		SingletonEnum;

		private CreateSingletonFromEnum createSingletonFromEnum;

		private MyEnumSingleton() {
			this.createSingletonFromEnum = new CreateSingletonFromEnum();
		}
		
		public CreateSingletonFromEnum getCreateSingletonFromEnum(){
			return createSingletonFromEnum;
		}

	}

	private CreateSingletonFromEnum() {
	}

	public static Object getInstance() {
		return MyEnumSingleton.SingletonEnum.getCreateSingletonFromEnum();
	}

}
