package com.hawk.framework.dic.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.design.database.Application;
import com.hawk.framework.dic.design.database.Schema;
import com.hawk.framework.dic.design.database.Table;
import com.hawk.framework.utility.packageTools;

public class ParseXmlService {
	
	private static String DATA_DEFINITION_FILE_SUFFIX = "data_defnition.xml";
	private static String TABLE_FILE_SUFFIX = "table.xml";
	private static String APPLICATION_FILE_SUFFIX = "application.xml";
	private static String SCHEMA_FILE_SUFFIX = "schema.xml";
	
	/**
	 * 转换xml 为 schema
	 * @param classPathList
	 * @return
	 */
	public List<Schema> parseSchemaByClassPath(List<String> classPathList ){
		return null;
	}
	
	public List<Schema> parseSchemaByClassPath(String classPath) throws Exception{
		List<String> xmlClassPathList = packageTools.listFile(classPath, true, new packageTools.XmlFileFilter());
		for (String xmlClassPath : xmlClassPathList){
			System.out.println(xmlClassPath);
		}
		
		return null;
	}
	
	private List<DataDefinition> parseDataDefinitionByClassPath(String classPath) throws Exception{
		
		List<DataDefinition> dataDefinitionList = new ArrayList<DataDefinition>();
		
		if (!classPath.toLowerCase().endsWith(DATA_DEFINITION_FILE_SUFFIX)){
			return dataDefinitionList;
		}
		
		SAXReader saxReader = new SAXReader();
		InputStream inputStream = getClass().getResourceAsStream(classPath);
		Document document = saxReader.read(inputStream);
		Element root = document.getRootElement();
		root.elements();
		
		
		return null;
	}
	
	public List<Table> parseTableByClassPath(String classPath){
		return null;
	}
	
	public List<Application> parseApplicationByClassPath(String classPath){
		return null;
	}

	public static void main(String[] args) throws Exception{
		new ParseXmlService().parseSchemaByClassPath("com.hawk.framework.dic.design");
	}
}
