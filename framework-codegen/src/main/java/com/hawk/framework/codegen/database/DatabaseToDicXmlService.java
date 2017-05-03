package com.hawk.framework.codegen.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.hawk.framework.codegen.database.config.DatabaseConfigure;
import com.hawk.framework.codegen.database.config.DbToDicConfigure;
import com.hawk.framework.codegen.database.config.IDatabaseConfigure;
import com.hawk.framework.codegen.database.config.IDbToDicConfigure;
import com.hawk.framework.codegen.database.config.IProjectConfigure;
import com.hawk.framework.codegen.database.config.ProjectConfigure;
import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.convert.TypeConverterFactory;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Index;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.utils.ProjectTools;
import com.hawk.framework.dic.design.Application;
import com.hawk.framework.dic.design.data.Word;
import com.hawk.framework.dic.service.deprecated.ParseXmlService;
import com.hawk.framework.dic.design.data.EnumDataType;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 将数据库映射成数据字典,并且保存为xml文件
 * 
 * @author pzhang1                    
 *
 */
@Deprecated
public class DatabaseToDicXmlService {

	private Configuration cfg = new Configuration();
	public DatabaseToDicXmlService(){
		cfg.setClassForTemplateLoading(DatabaseToDicXmlService.class, "");
		cfg.setNumberFormat("########");
	}
	

	/**
	 * 
	 * @param packageName 配置文件所在的package
	 * @throws Throwable
	 */
	public void execute(String packageName) throws Throwable {
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build(packageName);
		IProjectConfigure projectConfigure = ProjectConfigure.build(packageName);
		ITypeConverter typeConverter = TypeConverterFactory.build(databaseConfigure.getDialect());

		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);

		Date stdt = new Date();
		Database database = dbParser.parse();
		Date endt = new Date();
		System.out.println(
				"Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");

		/**
		 * 生成数据字典定义
		 */
		IDbToDicConfigure dbToDicConfigure = DbToDicConfigure.build(packageName);
		writeDictionary(database, projectConfigure, dbToDicConfigure, typeConverter);

	}
	
	/**
	 * 比较wor和column的数据类型是否完全一致
	 * @param def
	 * @param column
	 */
	private static boolean compareDataType(Word def ,Column column,ITypeConverter typeConverter){
		
		if (def.getDataType()==EnumDataType.String && column.getCharMaxLength() != def.getCharMaxLength()){
			System.out.println("CharMaxLength is not same");
			return false;		
		}
		if (def.getDataType()==EnumDataType.String && column.getCharMinLength() != def.getCharMinLength()){
			System.out.println("CharMinLength is not same");
			return false;
		}
		if (EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())) != def.getDataType()){
			System.out.println("DataType is not same");
			return false;
		}
		if (def.getDataType()==EnumDataType.Date && column.getDatetimePrecision() != def.getDatetimePrecision()){
			System.out.println("DatetimePrecision is not same");
			return false;
		}
//		if (!StringTools.compare(column.getComment(), def.getName())){
//			System.out.println("Comment is not same");
//			return false;
//		}
		if (def.getDataType()==EnumDataType.Numeric && column.getNumericPrecision() != def.getNumericPrecision()){
			System.out.println("NumericPrecision is not same");
			return false;
		}
		if (def.getDataType()==EnumDataType.Numeric && column.getNumericScale() != def.getNumericScale()){
			System.out.println("NumericScale is not same");
			return false;
		}
		
		return true;
	}
	
	
	
	private static Word convert(Column column, ITypeConverter typeConverter,IDbToDicConfigure dbToDicConfigure,String code) {
		Word word = dbToDicConfigure.findWord(code);	
		if (word != null)
			return word;
		word = new Word();
		
		word.setCharMaxLength(column.getCharMaxLength());
		word.setCharMinLength(column.getCharMinLength());
		word.setDataType(EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())));
		word.setDatetimePrecision(column.getDatetimePrecision());
		
				
		word.setId(UUID.randomUUID().toString());
		
		word.setCode(code);
		word.setName(column.getComment());
		word.setComment(column.getComment());
		word.setDisplayName(column.getComment());
//		word.setEnumKey(null);
//		word.setEnumValue(null);
		word.setIsEnum(0);
		word.setMaxValue(null);
		word.setMinValue(null);
		word.setIsOnlyAscii(1);
		word.setNumericPrecision(column.getNumericPrecision());
		word.setNumericScale(column.getNumericScale());

		word.setRegex(null);
		word.setUseType("technology");
		return word;
	}

	private void writeDictionary(Database database, IProjectConfigure projectConfigure, IDbToDicConfigure dbToDicConfigure, ITypeConverter typeConverter)
			throws Exception {
		Map<String, Word> defCodeMap = new HashMap<String, Word>();
		List<com.hawk.framework.dic.design.database.Table> dicTableList = new ArrayList<com.hawk.framework.dic.design.database.Table>();
		for (Table table : database.getTableList()) {
			System.out.println(table.getCode());
			/**
			 * 表
			 */
			com.hawk.framework.dic.design.database.Table dicTable = new com.hawk.framework.dic.design.database.Table();
			dicTable.setCode(table.getCode());
			dicTable.setType("normal");
			dicTable.setComment(table.getComment());
			dicTable.setId(UUID.randomUUID().toString());
			dicTableList.add(dicTable);

			/**
			 * 字段
			 */
			List<com.hawk.framework.dic.design.database.Column> dicColumnList = new ArrayList<com.hawk.framework.dic.design.database.Column>();
			Map<String, com.hawk.framework.dic.design.database.Column> dicColumnMap = new HashMap<String, com.hawk.framework.dic.design.database.Column>();
			dicTable.setColumnList(dicColumnList);
			for (Column column : table.getColumnList()) {
				String columnCode = column.getCode();
				String defCode = dbToDicConfigure.findSynonymCode(columnCode);
				Word def = null;
				/**
				 * defCode不为空，表示它有一个公用的数据字典定义code
				 */
				if (defCode != null && defCode.trim().length() > 0) {
					def = defCodeMap.get(defCode);
					if (def == null) {
						/**
						 * code设置成公用code
						 */
						def = convert(column, typeConverter,dbToDicConfigure,defCode);
						defCodeMap.put(defCode, def);
					}
				} else {
					defCode = columnCode;
					def = defCodeMap.get(defCode);
					if (def != null){
						/**
						 * 发现相同的code但是数据定义不同，报错
						 */
						if (!compareDataType(def,column,typeConverter)){
							throw new RuntimeException("Found duplicated word code = " + defCode);
						}
					}
					else {
						def = convert(column, typeConverter,dbToDicConfigure,defCode);
						defCodeMap.put(def.getCode(), def);
					}
				}
				com.hawk.framework.dic.design.database.Column dicColumn = new com.hawk.framework.dic.design.database.Column();
				dicColumnList.add(dicColumn);
				dicColumn.setWord(def);
				dicColumn.setNullable(column.getNullable());
				dicColumn.setIsPk(column.getIsPk());
				dicColumn.setId(UUID.randomUUID().toString());
				
				
				dicColumn.setCode(column.getCode());
				dicColumn.setName(column.getComment());
				dicColumn.setComment(column.getComment());
				dicColumnMap.put(dicColumn.getCode(), dicColumn);
			}

			/**
			 * 索引
			 */
			List<com.hawk.framework.dic.design.database.Index> dicIndexList = new ArrayList<com.hawk.framework.dic.design.database.Index>();
			dicTable.setIndexList(dicIndexList);
			for (Index index : table.getIndexList()) {
				com.hawk.framework.dic.design.database.Index dicIndex = new com.hawk.framework.dic.design.database.Index();
				dicIndexList.add(dicIndex);
				dicIndex.setCode(index.getCode());
				dicIndex.setIsPk(index.getIsPk());
				dicIndex.setIsUnique(index.getIsUnique());
				dicIndex.setId(UUID.randomUUID().toString());
				List<com.hawk.framework.dic.design.database.Column> dicIndexColumnList = new ArrayList<com.hawk.framework.dic.design.database.Column>();
				dicIndex.setColumnList(dicIndexColumnList);
				for (Column column : index.getColumnList()) {
					dicIndexColumnList.add(dicColumnMap.get(column.getCode()));
				}
			}
		}

		/**
		 * 输出所有的数据字典定义为一个文件
		 */
		Iterator<Word> it = defCodeMap.values().iterator();
		List<Word> list = new ArrayList<Word>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		writeWord(list, projectConfigure,dbToDicConfigure);

		/**
		 * 输出每个表为一个文件
		 */
		writeTable(dicTableList, projectConfigure);

		/**
		 * 输出Application,每个application一个文件
		 */
		List<Application> applicationList = new ArrayList<Application>();
		Application application = new Application();
		applicationList.add(application);
		application.setTableList(dicTableList);
		application.setId(UUID.randomUUID().toString());
		application.setCode("dic");
		application.setName("数据字典");
		application.setComment("元数据驱动开发测试");
		writeApplication(applicationList, projectConfigure);

		
	}

	

	private void writeApplication(List<Application> applicationList, IProjectConfigure projectConfigure) throws Exception {
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_application.ftl");
		
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.application");
		ProjectTools.clearDirectory(directory, "application.xml");
		for (Application application : applicationList) {
			String filePath = directory + File.separator + projectConfigure.getSubPackage() + ".application.xml";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);
			FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(application, out);
			out.flush();
			out.close();
		}
	}

	private void writeWord(List<Word> list, IProjectConfigure projectConfigure,IDbToDicConfigure dbtoDicConfig) throws Exception {
		
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_word.ftl");
		String wordProjectRootDirectory = ProjectTools.computeProjectRootDirectory(dbtoDicConfig.getWordProjectName());
		String directory = ProjectTools.computeProjectResourceDirectory(wordProjectRootDirectory,dbtoDicConfig.getWordPackage());	
		String fileName = projectConfigure.getRootPackage()+"."+projectConfigure.getSubPackage()+ ".word.xml";
		String filePath = directory + File.separator +fileName;
		
		/**
		 * 过滤掉已经在其它文件定义过的word
		 */
		
		Set<String> wordFileSet = dbtoDicConfig.getWordFiles();
		List<String> filteredWordFileList = new ArrayList<String>(); //不包含当前要写的文件
		for (String str : wordFileSet){
			if (!str.toLowerCase().endsWith(fileName.toLowerCase())){
				filteredWordFileList.add(str);
			}
		}
		List<Word> definedWordList =  new ParseXmlService().parseWordByClassPath(filteredWordFileList.toArray(new String[]{})); //已经定义过的word集合
		Set<String> definedWordCodeSet = new HashSet<String>();
		for (Word word : definedWordList){
			definedWordCodeSet.add(word.getCode());
		}		
		List<Word> wordList = new ArrayList<Word>();
		for (Word word : list){
			if (!definedWordCodeSet.contains(word.getCode())){
				wordList.add(word);
			}
		}
		Map<String, List<Word>> root = new HashMap<String, List<Word>>();
		root.put("wordList", wordList);
		
		/*
		 * 备份原文件
		 */		
		File backFile = new File(filePath+".back");
		if (backFile.exists())
			backFile.delete();
		new File(filePath).renameTo(backFile);
		
		
		new File(filePath).delete();
		if (new File(filePath).exists())
			throw new RuntimeException("Exists file = " + filePath);
		
		

		FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	
	private void writeTable(List<com.hawk.framework.dic.design.database.Table> dicTableList, IProjectConfigure projectConfigure) throws Exception {
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_table.ftl");
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.application.table");
		ProjectTools.clearDirectory(directory, ".table.xml");
		for (com.hawk.framework.dic.design.database.Table dicTable : dicTableList) {
			String filePath = directory + File.separator+projectConfigure.getSubPackage()+"." + dicTable.getCode() + ".table.xml";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);
			FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(dicTable, out);
			out.flush();
			out.close();
		}
	}
}
