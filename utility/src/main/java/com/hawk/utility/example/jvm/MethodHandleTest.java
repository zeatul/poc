package com.hawk.utility.example.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.*;


/**
 * MethodHandle模拟字节码层面的方法调用,是轻量级的
 * @author pzhang1
 *
 */
public class MethodHandleTest {

	static class ClassA{
		public void println(String s){
			System.out.println(s);
		}
	}
	
	public static void main(String[] args) throws Throwable{
		Object obj = System.currentTimeMillis() % 2 == 0  ? System.out : new ClassA();
		/**
		 * 无论obj是哪个实现类，下面的代码都能正确执行
		 */
		getPrintlnMH(obj).invokeExact("Hello MethodHandle");
	}
	
	private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException{
		/**
		 * MehtodType:代表“方法类型”，包含了方法的返回值(methodType()的第一个参数)和具体参数(methodtype()的第二及以后的参数)
		 */
		MethodType mt = MethodType.methodType(void.class,String.class);
		
		/**
		 * lookup()方法来自于MethodHandles.lookup,这句的作用是在指定类中查找符合给定的方法名称、方法类型,并且符合调用权限的方法句柄
		 */
		/**
		 * 因为这里调用的是一个虚方法,按照Java语言的规范,方法的第一个参数是隐式的，代表该方法的接收者，也即this指向的对象，这个参数以前是放在
		 * 参数列表进行传递的,而现在提供了bindTo()方法来完成这件事情
		 */
		return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
	}
}
