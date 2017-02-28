package com.hawk.framework.dic.service;

import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.design.database.Application;
import com.hawk.framework.dic.design.database.Column;
import com.hawk.framework.dic.design.database.Index;
import com.hawk.framework.dic.design.database.Schema;
import com.hawk.framework.dic.design.database.Table;
import com.hawk.framework.utility.BooleanTools;
import com.hawk.framework.utility.JsonTools;
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
	
	public List<Table> parseTableByClassPath(List<String> classPathList,Map<String,DataDefinition> dataDefinitionMap) throws Exception{
		List<Table> tableList = new ArrayList<Table>();
		for (String classPath : classPathList){
			if (!classPath.toLowerCase().endsWith(TABLE_FILE_SUFFIX)){
				continue;
			}		
			Table table =  parseTableByClassPath(classPath,dataDefinitionMap);			
			tableList.add(table);
		}
		return tableList;
	}
	
	public Table parseTableByClassPath(String classPath,Map<String,DataDefinition> dataDefinitionMap) throws Exception{
		if (!classPath.toLowerCase().endsWith(TABLE_FILE_SUFFIX)){
			throw new RuntimeException("The classPath is illegal for table ,classPath = " + classPath);
		}
		SAXReader saxReader = new SAXReader();
		InputStream inputStream = getClass().getResourceAsStream(classPath);
		Document document = saxReader.read(inputStream);
		Element tableElement = document.getRootElement();
		Table table = convert(tableElement, Table.class);
		/**
		 * column
		 */
		List<Column> columnList = new ArrayList<Column>();
		Map<String,Column> columnMap = new HashMap<String,Column>();
		table.setColumnList(columnList);
		@SuppressWarnings("unchecked")
		List<Element> columnElementList =  tableElement.element("columns").elements();
		for (Element columnElement : columnElementList){
			Column column = convert(columnElement,Column.class);
			String dataDefinitionId = columnElement.element("dataDefinition").elementTextTrim("id");
			DataDefinition dataDefinition = dataDefinitionMap.get(dataDefinitionId);
			if (dataDefinition == null)
				throw new RuntimeException(table.getCode() +" has illegal data definition id = "+dataDefinitionId  );
			column.setDataDefinition(dataDefinition);
			columnList.add(column);
			columnMap.put(column.getId(), column);
		}
		/**
		 * index
		 */
		List<Index> indexList = new ArrayList<Index>();
		@SuppressWarnings("unchecked")
		List<Element> indexElementList = tableElement.element("indexes").elements();
		for (Element indexElement : indexElementList){
			Index index = convert(indexElement,Index.class);
			indexList.add(index);
			List<Column> indexColumnList = new ArrayList<Column>();
			index.setColumnList(indexColumnList);
			@SuppressWarnings("unchecked")
			List<Element> indexColumnElementList =  indexElement.element("columns").elements();
			for (Element indexColumnElement : indexColumnElementList){
				String indexColumnId = indexColumnElement.elementTextTrim("id");
				Column indexColumn = columnMap.get(indexColumnId);
				if (indexColumn == null)
					throw new RuntimeException("Illega index column id = "+indexColumnId);
				indexColumnList.add(indexColumn);
			}
		}
		
		return table;
	}
	
	/**
	 * 批量解析 data_definition.xml文件
	 * @param classPathList
	 * @return
	 * @throws Exception
	 */
	public List<DataDefinition> parseDataDefinitionByClassPath(List<String> classPathList) throws Exception{
		
		List<DataDefinition> dataDefinitionList = new ArrayList<DataDefinition>();
		for (String classPath : classPathList){
			if (!classPath.toLowerCase().endsWith(DATA_DEFINITION_FILE_SUFFIX)){
				continue;
			}		
			List<DataDefinition> list =  parseDataDefinitionByClassPath(classPath);
			
			dataDefinitionList.addAll(list);
		}		
		
		return dataDefinitionList;
	}
	
	/**
	 * 解析单个data_definition.xml文件
	 * @param classPath
	 * @return
	 * @throws Exception
	 */
	public List<DataDefinition> parseDataDefinitionByClassPath(String classPath)throws Exception{
		if (!classPath.toLowerCase().endsWith(DATA_DEFINITION_FILE_SUFFIX)){
			throw new RuntimeException("The classPath is illegal for data definition,classPath = " + classPath);
		}
		
		List<DataDefinition> dataDefinitionList = new ArrayList<DataDefinition>();
		SAXReader saxReader = new SAXReader();
		InputStream inputStream = getClass().getResourceAsStream(classPath);
		Document document = saxReader.read(inputStream);
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		for (Element element : elementList){
			 DataDefinition dataDefinition = convert(element,DataDefinition.class);
			 dataDefinitionList.add(dataDefinition);
		}
		return dataDefinitionList;
	}
	
	private <T> T convert(Element element,Class<T> clazz) throws Exception{
		
		T result = clazz.newInstance();
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields){
			String fieldName = field.getName();
			
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method writer = pd.getWriteMethod();
			if (writer == null)
				continue;
			
			String value = element.elementTextTrim(fieldName);
			if (value == null || value.length() == 0)
				continue;
			value = value.trim();
			Class<?> fieldClass = field.getType();
			Object fieldValue = null;
			if (fieldClass == Integer.class){
				
				try {
					fieldValue = Integer.parseInt(value);
				} catch (Exception e) {
					fieldValue = BooleanTools.convet(value);
				}
			}else if (fieldClass == String.class){
				fieldValue = value;
			}else{
				/**
				 * TODO:等待支持其它类型
				 */
			}
			
			writer.invoke(result, new Object[]{fieldValue});
		}
		return result;
	}
	
	
	
	
	
	public List<Application> parseApplicationByClassPath(String classPath){
		return null;
	}

	public static void main(String[] args) throws Exception{
		System.out.println("+++data definition+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<String> xmlClassPathList = packageTools.listFile("com.hawk.framework.dic.design", true, new packageTools.XmlFileFilter());
		List<DataDefinition> dataDefinitionList = new ParseXmlService().parseDataDefinitionByClassPath(xmlClassPathList);
		for (DataDefinition dataDefinition : dataDefinitionList){
			System.out.println(JsonTools.toJsonString(dataDefinition));
		}
		
		Map<String,DataDefinition> dataDefinitionMap = new HashMap<String,DataDefinition>();
		for (DataDefinition dataDefinition : dataDefinitionList){
			dataDefinitionMap.put(dataDefinition.getId(), dataDefinition);
		}
		
		System.out.println("+++table+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		List<Table> tableList =new ParseXmlService(). parseTableByClassPath(xmlClassPathList,dataDefinitionMap);
		for (Table table : tableList){
			System.out.println(JsonTools.toJsonString(table));
		}
	}
}
