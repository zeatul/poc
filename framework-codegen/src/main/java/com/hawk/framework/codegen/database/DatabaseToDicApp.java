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
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.utils.ProjectTools;
import com.hawk.framework.dic.data.DataDefinition;
import com.hawk.framework.dic.data.EnumDataType;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 将数据库映射成数据字典
 * 
 * @author pzhang1
 *
 */
public class DatabaseToDicApp {
	
	

	private static Configuration cfg = new Configuration();
	static {
		cfg.setClassForTemplateLoading(DatabaseToCodeApp.class, "");
	}

	public static void main(String[] args) {
		try {
			run();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.exit(0);
	}

	private static void run() throws Throwable {
		IDatabaseConfigure databaseConfigure = DatabaseConfigure.build();
		IProjectConfigure projectConfigure = ProjectConfigure.build();
		ITypeConverter typeConverter = TypeConverterFactory.build(databaseConfigure.getDialect());

		IDatabaseParser dbParser = DatabaseParserFactory.build(databaseConfigure);

		Date stdt = new Date();
		Database database = dbParser.parse();
		Date endt = new Date();
		System.out.println(
				"Success parse database , find " + database.getTableList().size() + " tables, cost " + (endt.getTime() - stdt.getTime()) + " millseconds");
		
				
		/**
		 * 生成数据字典定义，描述每个字段
		 */
		IDbToDicConfigure dbToDicConfigure = DbToDicConfigure.build();
		writeDataDefinition(database,projectConfigure,dbToDicConfigure,typeConverter);
		
		/**
		 * 生成数据库定义
		 */
	}
	
	private static DataDefinition convert(Column column,ITypeConverter typeConverter){
		DataDefinition def = new DataDefinition();
		def.setCharMaxLength(column.getCharMaxLength());
		def.setCharMinLength(column.getCharMinLength());
		def.setDataType(EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())));
		def.setDatetimePrecision(column.getDatetimePrecision());
		def.setDescription(column.getComment());
		def.setDisplayName(column.getComment());
		def.setEnumKey(null);
		def.setEnumValue(null);
		def.setIsEnum(0);
		def.setMaxValue(null);
		def.setMinValue(null);
		def.setNumericPrecision(column.getNumericPrecision());
		def.setNumericScale(column.getNumericScale());
		def.setObjectId(UUID.randomUUID().toString());
		def.setObjectLabel(column.getName());
		def.setObjectName(column.getComment());
		def.setRegex(null);
		def.setUseType("technology");
		return def;
	}
	
	private static void writeDataDefinition(Database database,IProjectConfigure projectConfigure,IDbToDicConfigure dbToDicConfigure,ITypeConverter typeConverter) throws Exception{
		Map<String,DataDefinition> defNameMap = new HashMap<String,DataDefinition>();
		List<com.hawk.framework.dic.database.Table> dicTableList = new ArrayList<com.hawk.framework.dic.database.Table>();
		for (Table table : database.getTableList()){
			com.hawk.framework.dic.database.Table dicTable = new com.hawk.framework.dic.database.Table();
			dicTable.setName(table.getName());
			dicTable.setType("normal");
			dicTable.setComment(table.getComment());
			dicTableList.add(dicTable);
			List<com.hawk.framework.dic.database.Column> dicColumnList = new ArrayList<com.hawk.framework.dic.database.Column>();
			dicTable.setCloumnList(dicColumnList);
			for (Column column: table.getColumnList()){
				String columnName = column.getName();
				String defName= dbToDicConfigure.computeDataDefinitionName(columnName);
				DataDefinition def = null;
				/**
				 * defName不为空,表示这个字段的定义是公用的,需要从map里取
				 */
				if (defName != null && defName.trim().length() > 0){
					def = defNameMap.get(defName);					
					if (def == null){
						def = convert(column,typeConverter);
						def.setObjectLabel(defName);
						defNameMap.put(defName,def);
					}
				}
				else{
					def = convert(column,typeConverter);
					defNameMap.put(def.getObjectLabel(), def);
				}
				com.hawk.framework.dic.database.Column dicColumn = new com.hawk.framework.dic.database.Column();
				dicColumnList.add(dicColumn);
				dicColumn.setDataDefinition(def);
				dicColumn.setNullable(column.getNullable());
				dicColumn.setPk(column.getPk());
				dicColumn.setObjectId(UUID.randomUUID().toString());
				dicColumn.setName(column.getName());
			}
		}
		
		/**
		 * 输出所有的数据字典定义为一个文件
		 */
		Map<String,List<DataDefinition>> root = new HashMap<String,List<DataDefinition>>();
		Iterator<DataDefinition> it = defNameMap.values().iterator();
		List<DataDefinition> list = new ArrayList<DataDefinition>();
		while(it.hasNext()){
			list.add(it.next());
		}
		root.put("dataDefnitionList", list);
		/* 获取或创建模板 */
		Template template = cfg.getTemplate("template/dic_data_defnition.ftl");
		String directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.data");
		ProjectTools.clearDirectory(directory, "data_defnition.xml");
		String filePath = directory + File.separator + "data_defnition.xml";
		if (new File(filePath).exists())
			throw new RuntimeException("Exists file = " + filePath);

		FileOutputStream fileOutputStream = new FileOutputStream(filePath, false);
		OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
		template.process(root, out);
		out.flush();
		out.close();
		
		/**
		 * 每个表为一个文件
		 */
		/* 获取或创建模板 */
		template = cfg.getTemplate("template/dic_table.ftl");
		directory = ProjectTools.computeProjectResourceDirectory(projectConfigure.getProjectRootDirectory(), projectConfigure.getRootPackage(),
				projectConfigure.getSubPackage(), "design.database");
		ProjectTools.clearDirectory(directory, ".table.xml");
		for (com.hawk.framework.dic.database.Table dicTable : dicTableList){
			filePath = directory + File.separator+dicTable.getName() + ".table.xml";
			if (new File(filePath).exists())
				throw new RuntimeException("Exists file = " + filePath);
			fileOutputStream = new FileOutputStream(filePath, false);
			out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			template.process(dicTable, out);
			out.flush();
			out.close();
		}
	}
}
