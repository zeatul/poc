package com.ccb.acten.freemarker;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreeMarker {
	
	public static class Person{
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		private String firstName;
		private String lastName;
		private String sex = "Male";
		private String birthday="2017-12-19";
		
		public static Person buildPerson(String firstName,String lastName){
			Person person = new Person();
			person.setFirstName(firstName);
			person.setLastName(lastName);
			return person;
		}
	}
	
	public static Map<String,Object> buildDataModel(Object object){
		Map<String,Object> dataModel = new HashMap();
		Map<Object,Object> map = new BeanMap(object);
		
		List<String> keys = new ArrayList<String>();
		for (Object key : map.keySet()){
			keys.add((String)key);
		}
		dataModel.put("keys", keys);
		dataModel.put("values", map);
		return dataModel;
		
	}
	
	public static void main(String[] args) throws Exception{
		Person person = Person.buildPerson("Tom", "Cat");
		Map<String,Object> dataModel = buildDataModel(person);
		stringConvert(dataModel);
	}
	
	public static void stringConvert(Map<String,Object> dataModel) throws Exception{
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(TestFreeMarker.class, "");
		Template template = cfg.getTemplate("xml.ftl");
		template.setEncoding("UTF-8");
		
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);	
		template.process(dataModel, writer);		
		writer.flush();
		writer.close();
		
		System.out.println(stringWriter.toString());
	}
	
	public static void fileConvert() throws IOException, TemplateException{
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(TestFreeMarker.class, "");
		Template template = cfg.getTemplate("xml.ftl");
		Map<String,Object> dataModel = new HashMap<String,Object>();
		String filePath = "c:/out.xml";
		FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");		
		template.process(dataModel, out);
		
		out.flush();
		out.close();
	}

}
