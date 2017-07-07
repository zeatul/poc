package com.hawk.framework.utility.tools;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainTools {

	/**
	 * 将来源数据里的同名field拷贝给目标对象(浅拷贝,简单对象)
	 * 
	 * @param source
	 *            来源数据
	 * @param clazz
	 *            目标对象的class
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T copy(Object source, Class<T> clazz) throws Exception {
		T target = clazz.newInstance();
		copy(source, target);

		return target;
	}

	/**
	 * 对象转map
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> toMap(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();

		Map<String, Object> map = new HashMap<String,Object>();
		for (Field field : fields) {
			String fieldName = field.getName();
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method reader = pd.getReadMethod();
			if (reader == null) {
				continue;
			}
			Object value = reader.invoke(object, new Object[] {});
			if (value == null)
				continue;
			
			map.put(fieldName, value);
		}
		return map;
	}
	
	/**
	 * 构造签名用的字符串
	 * 1.先将object转为map
	 * 2.再按照key升序排序
	 * 3.再按照key获取value并将key和value拼接：key1value1key2value2.。。。。。。。
	 * @param object
	 * @return 构造完成的签名字符串，按照key升序排列，key1value1key2value2.。。。。。。。keynvaluen
	 * @throws Exception
	 */
	public static String buildSignString(Object object)throws Exception{
		Map<String, Object> map = toMap(object);
		List<String> keyList = new ArrayList<String>();
		map.keySet().forEach(e->{
			keyList.add(e);
		});
		Collections.sort(keyList);
		List<String> list = new ArrayList<String>();
		keyList.forEach(key->{
			Object value = map.get(key);
			if (value != null){
				list.add(key);
				list.add(value.toString());				
			}
		});
		return StringTools.concat(list);
	}
	

	/**
	 * map转对象
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T copy(Map<String, Object> map, Class<T> clazz) throws Exception {
		Field[] fields = clazz.getDeclaredFields();

		T t = clazz.newInstance();

		for (Field field : fields) {
			String fieldName = field.getName();
			try {
				Object value = map.get(fieldName);

				if (value == null)
					continue;

				PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
				Method writer = pd.getWriteMethod();
				if (writer == null)
					continue;

				writer.invoke(t, new Object[] { value });

			} catch (Exception e) {

			}
		}

		return t;

	}

	/**
	 * 将来源数据里的同名field拷贝给目标对象(浅拷贝,简单对象)
	 * 
	 * @param source
	 * @param target
	 */
	public static void copy(Object source, Object target) {
		Class<?> sourceClass = source.getClass();
		Class<?> targetClass = target.getClass();

		Field[] sourceFileds = sourceClass.getDeclaredFields();
		Field[] fields = targetClass.getDeclaredFields();
		if (fields.length > sourceFileds.length) {
			fields = sourceFileds;
		}

		for (Field field : fields) {
			String fieldName = field.getName();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(fieldName, sourceClass);
				Method reader = pd.getReadMethod();
				if (reader == null)
					continue;

				pd = new PropertyDescriptor(fieldName, targetClass);
				Method writer = pd.getWriteMethod();
				if (writer == null)
					continue;

				Object value = reader.invoke(source, new Object[] {});
				if (value == null)
					continue;

				writer.invoke(target, new Object[] { value });

			} catch (Exception e) {

			}
		}
	}

	/**
	 * 集合复制(浅拷贝,简单对象)
	 * 
	 * @param sources
	 *            源对象集合
	 * @param targets
	 *            目标对象的空集合 size()=0
	 * @param clazz
	 *            目标对象类型
	 */
	public static <K, V> void copy(Collection<K> sources, Collection<V> targets, Class<V> clazz) {

		targets.clear();

		for (K source : sources) {

			V target;
			try {
				target = clazz.newInstance();
				copy(source, target);
				targets.add(target);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

	}

	/**
	 * 集合复制(浅拷贝,简单对象)
	 * 
	 * @param sources
	 *            源对象集合
	 * @param clazz
	 *            目标对象类型
	 * @return 拷贝后的目标对象集合
	 */
	public static <K, V> List<V> copy(Collection<K> sources, Class<V> clazz) {
		int size = sources == null ? 1 : sources.size();
		List<V> target = new ArrayList<V>(size);
		copy(sources, target, clazz);
		return target;
	}

}
