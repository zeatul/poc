package com.hawk.framework.codegen.database.convert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.codegen.database.meta.Domain;
import com.hawk.framework.codegen.database.meta.Field;
import com.hawk.framework.codegen.database.meta.Index;
import com.hawk.framework.codegen.database.meta.Table;
import com.hawk.framework.codegen.utils.BooleanTools;
import com.hawk.framework.codegen.utils.CamelNameTools;

public class DomainConverter implements IDomainConverter{
	
	private String tableNameSpliter = "_";
	private int tableNameStartIndex = 2;
	private String columnNameSpliter = "_";
	private int columnNameStartIndex = 0;
	
	private ITypeConverter typeConverter;
	
	public DomainConverter(ITypeConverter typeConverter){
		this.typeConverter = typeConverter;
	}
	
	

	public Domain convert(Table table) {
		if (table == null)
			throw new RuntimeException("The table is null!");
		
		Domain domain = new Domain();
		
		/**
		 * table to domain
		 */
		String tableName = table.getName();
		String className = CamelNameTools.camelName(tableName, tableNameSpliter, tableNameStartIndex, true);		
		domain.setTableName(tableName);
		domain.setClassName(className);
		domain.setDesc(table.getComment());
		
		/**
		 * column to field
		 */
		List<Field> fieldList = domain.getFieldList();
		Set<String> fieldTypeSet = new HashSet<String>();
		for (Column column : table.getColumnList()){
			Field field = new Field();
			fieldList.add(field);
			String columnName = column.getName();
			String fieldName = CamelNameTools.camelName(columnName, columnNameSpliter, columnNameStartIndex, false);	
			field.setColumnName(columnName);
			field.setFieldDesc(column.getComment());
			field.setFieldName(fieldName);
			
			String dbType = column.getDataType();
			field.setDbType(dbType);
			field.setFieldType(typeConverter.convertFromDbToJava(dbType));
			field.setFieldJdbcType(typeConverter.convertFromDbToJdbc(dbType));
			fieldTypeSet.add(field.getFieldType());
			field.setCharMaxLength(column.getCharMaxLength());
			field.setColumnType(column.getColumnType());
			field.setDatetimePrecision(column.getDatetimePrecision());
			field.setNullable(column.getNullable());
			field.setNumericPrecision(column.getNumericPrecision());
			field.setNumericScale(column.getNumericScale());
			field.setPk(column.getPk());
		}
		
		/**
		 * import List ;
		 */
		List<String> importList = domain.getImportList();
		for(String fieldType : fieldTypeSet){
			if (fieldType.equals("Date")){
				importList.add("java.util.Date");
			}else if (fieldType.equals("BigDecimal")){
				importList.add("java.math.BigDecimal");
			}
		}
		
		/**
		 * key
		 */
		List<Index> indexList = table.getIndexList();
		for (Index index : indexList){
			if (BooleanTools.parse(index.getPk())){ 
				List<Field> keyList =  domain.getKeyList();
				Map<String,Field> map = new HashMap<String,Field>();
				for (Field field : fieldList){
					map.put(field.getColumnName(), field);
				}
				for (Column column : index.getColumnList()){
					Field field = map.get(column.getName());
					if (field == null)
						throw new RuntimeException("the index's column is illegal ,can't be found in the fieldList");
					keyList.add(field);
				}
				
				break;
			}
		}
		
		return domain;
	}

}
