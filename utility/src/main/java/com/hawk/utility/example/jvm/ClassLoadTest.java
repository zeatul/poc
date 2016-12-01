package com.hawk.utility.example.jvm;

import java.io.IOException;
import java.io.InputStream;

/**
 * 只有在同一个类加载器下的类才可能相等
 * @author pzhang1
 *
 */
public class ClassLoadTest {

	public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					} else {
						System.out.println("New class loader run");
					}

					byte[] b = new byte[is.available()];
					is.read(b);

					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException();
				}

			}
		};
		
		Object obj = myLoader.loadClass("com.hawk.utility.example.jvm.ClassLoadTest").newInstance();
		
		System.out.println(obj.getClass());
		
		System.out.println(obj instanceof com.hawk.utility.example.jvm.ClassLoadTest);
	}
}
