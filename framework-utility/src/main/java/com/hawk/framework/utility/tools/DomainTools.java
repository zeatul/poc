package com.hawk.framework.utility.tools;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DomainTools {
	
	/**
	 * 将来源数据里的同名field拷贝给目标对象(浅拷贝,简单对象)
	 * @param source 来源数据
	 * @param clazz 目标对象的class
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static  <T> T copy(Object source , Class<T> clazz) throws Exception{
		T target = clazz.newInstance();
		copy(source,target);
		
		return target;
	}
	
	/**
	 * 将来源数据里的同名field拷贝给目标对象(浅拷贝,简单对象)
	 * @param source
	 * @param target
	 */
	public static void copy(Object source , Object target){
		Class<?> sourceClass = source.getClass();
		Class<?> targetClass = target.getClass();
		
		
		Field[] sourceFileds = sourceClass.getDeclaredFields();
		Field[] fields = targetClass.getDeclaredFields(); 
		if (fields.length > sourceFileds.length){
			fields = sourceFileds;
		}
		
		for (Field field : fields){
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
				
				Object value = reader.invoke(source, new Object[]{});
				if (value == null)
					continue;
				
				writer.invoke(target, new Object[]{value});
				
			} catch (Exception e) {
				
			}
		}
	}
	
	/**
	 * 集合复制(浅拷贝,简单对象)
	 * @param sources 源对象集合
	 * @param targets 目标对象的空集合 size()=0
	 * @param clazz 目标对象类型
	 */
	public static <K,V> void copy(Collection<K> sources ,Collection<V> targets , Class<V> clazz){
		
		targets.clear();
		
		for (K source : sources){
			
			V target;
			try {
				target =  clazz.newInstance();
				copy(source,target);				
				targets.add(target);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			
		}
		
	}
	
	/**
	 * 集合复制(浅拷贝,简单对象)
	 * @param sources 源对象集合
	 * @param clazz 目标对象类型 
	 * @return 拷贝后的目标对象集合
	 */
	public static <K,V> List<V> copy(Collection<K> sources, Class<V> clazz){
		int size = sources == null ? 1 : sources.size();
		List<V> target = new ArrayList<V>(size);
		copy(sources,target,clazz);
		return target;
	}

}
