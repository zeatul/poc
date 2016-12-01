package com.hawk.utility.example.jvm;

/**
 * 测试静态域的初始化
 * 子类会先调用父类的静态初始化函数
 * 如果是final域，不会触发类的初始化动作
 * @author pzhang1
 *
 */
public class TestClassInitialize {

	public static class SuperClass{
		static{
			System.out.println("SuperClass init!");
		}
		
		public final static int value = 123;
	}
	
	public static class SubClass extends SuperClass{
		static{
			System.out.println("SubClass init!");
		}
	}
	
	public static void main(String[] args){
		System.out.println(SubClass.value);
	}
}
