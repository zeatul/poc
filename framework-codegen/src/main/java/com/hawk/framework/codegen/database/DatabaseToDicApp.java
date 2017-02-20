package com.hawk.framework.codegen.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.hawk.framework.codegen.database.convert.DomainConverterFactory;
import com.hawk.framework.codegen.database.convert.IDomainConverter;
import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.convert.TypeConverterFactory;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Database;
import com.hawk.framework.codegen.database.meta.Domain;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.database.parse.DatabaseParserFactory;
import com.hawk.framework.codegen.database.parse.IDatabaseParser;
import com.hawk.framework.codegen.utils.JsonTools;
import com.hawk.framework.dic.data.DataDefinition;
import com.hawk.framework.dic.data.EnumDataType;
import com.hawk.framework.dic.database.converter.TypeConverter;

import freemarker.template.Configuration;

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
		writeDataDefinition(database,dbToDicConfigure);
		
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
		def.setUseType(0);
		return def;
	}
	
	private static void writeDataDefinition(Database database,IDbToDicConfigure dbToDicConfigure,ITypeConverter typeConverter){
		Map<String,DataDefinition>	map = new HashMap<String,DataDefinition>();			
		for (Table table : database.getTableList()){
			for (Column column: table.getColumnList()){
				String columnName = column.getName();
				String defName= dbToDicConfigure.computeDataDefinitionName(columnName);
				if (defName != null && defName.trim().length() > 0){
					DataDefinition def = map.get(defName);
					if (def == null){
						def = convert(column,typeConverter);
						map.put(defName,def);
					}
				}
			}
		}
		XStream xstream;
	}
}
