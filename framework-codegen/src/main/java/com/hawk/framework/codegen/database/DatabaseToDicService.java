package com.hawk.framework.codegen.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
import com.hawk.framework.dic.design.data.DataDefinition;
import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.utility.StringTools;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 将数据库映射成数据字典
 * 
 * @author pzhang1
 *
 */
public class DatabaseToDicService {

	private Configuration cfg = new Configuration();
	public DatabaseToDicService(){
		cfg.setClassForTemplateLoading(DatabaseToDicService.class, "");
		cfg.setNumberFormat("########");
	}
	

	/**
	 * 配置文件所在的package
	 * @param packageName
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
	 * 比较DataDefinition和column的数据类型是否完全一致
	 * @param def
	 * @param column
	 */
	private static boolean compareDataType(DataDefinition def ,Column column,ITypeConverter typeConverter){
		
		if (column.getCharMaxLength() != def.getCharMaxLength())
			return false;		
		if (column.getCharMinLength() != def.getCharMinLength())
			return false;
		if (EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())) != def.getDataType())
			return false;
		if (column.getDatetimePrecision() != def.getDatetimePrecision())
			return false;
		if (!StringTools.compare(column.getComment(), def.getName()))
			return false;
		if (column.getNumericPrecision() != def.getNumericPrecision())
			return false;
		if (column.getNumericScale() != def.getNumericScale())
			return false;
		
		return true;
	}
	
	
	
	private static DataDefinition convert(Column column, ITypeConverter typeConverter,IDbToDicConfigure dbToDicConfigure,String code) {
		DataDefinition def = new DataDefinition();
		def.setCharMaxLength(column.getCharMaxLength());
		def.setCharMinLength(column.getCharMinLength());
		def.setDataType(EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())));
		def.setDatetimePrecision(column.getDatetimePrecision());
		
		String id = dbToDicConfigure.findId(code);				
		def.setId(id==null?UUID.randomUUID().toString():id);
		
		def.setCode(code);
		def.setName(column.getComment());
		def.setComment(column.getComment());
		def.setDisplayName(column.getComment());
		def.setEnumKey(null);
		def.setEnumValue(null);
		def.setIsEnum(0);
		def.setMaxValue(null);
		def.setMinValue(null);
		def.setIsOnlyAscii(1);
		def.setNumericPrecision(column.getNumericPrecision());
		def.setNumericScale(column.getNumericScale());

		def.setRegex(null);
		def.setUseType("technology");
		return def;
	}

	private void writeDictionary(Database database, IProjectConfigure projectConfigure, IDbToDicConfigure dbToDicConfigure, ITypeConverter typeConverter)
			throws Exception {
		Map<String, DataDefinition> defCodeMap = new HashMap<String, DataDefinition>();
		List<com.hawk.framework.dic.design.database.Table> dicTableList = new ArrayList<com.hawk.framework.dic.design.database.Table>();
		for (Table table : database.getTableList()) {
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
				DataDefinition def = null;
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
							throw new RuntimeException("Found duplicated data defintion code = " + defCode);
						}
					}
					else {
						def = convert(column, typeConverter,dbToDicConfigure,defCode);
						defCodeMap.put(def.getCode(), def);
					}
				}
				com.hawk.framework.dic.design.database.Column dicColumn = new com.hawk.framework.dic.design.database.Column();
				dicColumnList.add(dicColumn);
				dicColumn.setDataDefinition(def);
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
		Iterator<DataDefinition> it = defCodeMap.values().iterator();
		List<DataDefinition> list = new ArrayList<DataDefinition>();
		while (it.hasNext()) {
			list.add(it.next());
		}
		writeDataDefinition(list, projectConfigure);
		
		/**
		 * 输出所有的所有数据字典的code为一个文件
		 */
		writeWord(list, projectConfigure);

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

	private void writeDataDefinition(List<DataDefinition> list, IProjectConfigure projectConfigure) throws Exception {
		Map<String, List<DataDefinition>> root = new HashMap<String, List<DataDefinition>>();
		root.put("dataDefnitionList", list);
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_data_defnition.ftl");
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.data");
		ProjectTools.clearDirectory(directory, "data_defnition.xml");
		String filePath = directory + File.separator +projectConfigure.getSubPackage()+ ".data_defnition.xml";
		if (new File(filePath).exists())
			throw new RuntimeException("Exists file = " + filePath);

		FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	private void writeWord(List<DataDefinition> list ,IProjectConfigure projectConfigure) throws Exception{
		Map<String, List<DataDefinition>> root = new HashMap<String, List<DataDefinition>>();
		root.put("dataDefnitionList", list);
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_word.ftl");
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.word");
		ProjectTools.clearDirectory(directory, "word.xml");
		String filePath = directory + File.separator+projectConfigure.getRootPackage()+"." +projectConfigure.getSubPackage()+ ".word.xml";
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
