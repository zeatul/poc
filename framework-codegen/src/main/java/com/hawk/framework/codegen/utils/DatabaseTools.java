package com.hawk.framework.codegen.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.hawk.framework.codegen.database.convert.ITypeConverter;
import com.hawk.framework.codegen.database.meta.Column;
import com.hawk.framework.dic.design.data.EnumDataType;
import com.hawk.framework.dic.design.data.Word;

public class DatabaseTools {
	
	/**
	 * 将column集合转换成map key=code,value=column
	 * @param columnList
	 * @return
	 */
	public static Map<String,Column> convert(List<Column> columnList){
		Map<String,Column> map = new HashMap<String,Column>();
		for (Column column : columnList){
			map.put(column.getCode(), column);
		}
		return map;
	}
	
	
	public static Word convert(Column column, ITypeConverter typeConverter) {
		Word word = new Word();
		
		word.setCharMaxLength(column.getCharMaxLength());
		word.setCharMinLength(column.getCharMinLength());
		word.setDataType(EnumDataType.parse(typeConverter.convertFromDbToJava(column.getDataType())));
		word.setDatetimePrecision(column.getDatetimePrecision());		
				
		word.setId(UUID.randomUUID().toString());
		
		word.setCode(column.getCode());
		word.setName(column.getComment());
		word.setComment(column.getComment());
		word.setDisplayName(column.getComment());
		
		
//		word.setEnumValues(null);
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

}
