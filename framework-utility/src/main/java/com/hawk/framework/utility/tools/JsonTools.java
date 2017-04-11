package com.hawk.framework.utility.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTools {

	protected final static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL); 
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		objectMapper.setDateFormat(df);
		
	}

	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	public static <T> T toObject(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> Collection<T> toObject (String jsonStr, Class<? extends Collection<?>> cClass,Class<T> tClass){
		try {
			JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(cClass, tClass);
			return objectMapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> ArrayList<T> toArrayList(String jsonStr ,Class<T> clazz){
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> HashSet<T> toHashSet(String jsonStr , Class<T> clazz){
		JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(HashSet.class, clazz);
		try {
			return objectMapper.readValue(jsonStr, javaType);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	public static <T> List<T> toList(String jsonStr, final Class<T> clazz){
//		return objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
//		});
//	}
	
	public static class T{
		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
		private Date date;
	}

	public static void main(String[] args){
		List<Object> list = new ArrayList<Object>();
		
		list.add("onDxbt5qLOjAdkqE9E_1MOhzayHU");
		list.add("onDxbt5qLOjAdkqE9E_1MOh123HU");
		
		list.add(new Date());
		
		T t = new T();
		t.setDate(new Date());
		
		list.add(t);
		
		String str = JsonTools.toJsonString(list);
		System.out.println(str);
		
		
		
	}
}
