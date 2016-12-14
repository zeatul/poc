package com.hawk.utility.example.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java动态代理示例
 * @author pzhang1
 *
 */
public class DynamicProxyTest {

	interface IHello {
		void sayHello();
	}

	static class Hello implements IHello {

		@Override
		public void sayHello() {
			System.out.println("Hello World!");
		}

	}

	static class DynamicProxy implements InvocationHandler {
		Object orginalObject;

		Object bind(Object originalObject) {
			this.orginalObject = originalObject;

			return Proxy.newProxyInstance(orginalObject.getClass().getClassLoader(), orginalObject.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Welcome");
			return method.invoke(orginalObject, args);
		}
	}
	
	public static void main(String[] args){
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		IHello ihello = (IHello)new DynamicProxy().bind(new Hello());
		ihello.sayHello();
		
	}
}
