package com.hawk.framework.codegen.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.framework.codegen.database.meta.Column;

public class DatabaseTools {
	
	public static Map<String,Column> convert(List<Column> columnList){
		Map<String,Column> map = new HashMap<String,Column>();
		for (Column column : columnList){
			map.put(column.getName(), column);
		}
		return map;
	}

}
