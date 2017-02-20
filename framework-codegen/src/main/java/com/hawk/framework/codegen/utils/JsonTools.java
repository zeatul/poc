package com.hawk.framework.codegen.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

@SuppressWarnings("deprecation")
public class JsonTools {

	protected final static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_EMPTY);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		objectMapper.getSerializationConfig().setDateFormat(df);
		objectMapper.getDeserializationConfig().setDateFormat(df);
		objectMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/*
		 * objectMapper.getSerializationConfig().set(
		 * org.codehaus.jackson.map.SerializationConfig
		 * .Feature.FAIL_ON_EMPTY_BEANS, false);
		 */
	}

	public static String toJsonString(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T toObject(String jsonStr, Class<?> clazz) {
		try {
			return (T)objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> Collection<T> toObject (String jsonStr, Class<? extends Collection> cClass,Class<T> tClass){
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

	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		
		list.add("onDxbt5qLOjAdkqE9E_1MOhzayHU");
		list.add("onDxbt5qLOjAdkqE9E_1MOh123HU");
		
		String str = JsonTools.toJsonString(list);
		System.out.println(str);
		
		
		
	}
}
