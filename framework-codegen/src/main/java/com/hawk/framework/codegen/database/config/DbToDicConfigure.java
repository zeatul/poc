package com.hawk.framework.codegen.database.config;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class DbToDicConfigure implements IDbToDicConfigure {

	@Override
	public String computeDataDefinitionName(String columnName) {
		if (shareColumnNameSet.contains(columnName))
			return columnName;
		for (String suffix : shareColumnNameSuffixList){
			if (columnName.endsWith(suffix))
				return suffix;
		}
		return null;
	}

	private Set<String> shareColumnNameSet = new HashSet<String>();
	private List<String> shareColumnNameSuffixList = new ArrayList<String>();

	public static DbToDicConfigure build() {
		DbToDicConfigure dbToDicConfigure = new DbToDicConfigure();
		/**
		 * 读取配置文件
		 */
		Properties props = new Properties();
		InputStream in = new BufferedInputStream(DatabaseConfigure.class.getResourceAsStream("db_to_dic.properties"));

		try {
			props.load(in);
			String shareColumnNames = props.getProperty("share_column_names");
			String[] strArray = shareColumnNames.split(",");
			for (String str : strArray) {
				dbToDicConfigure.shareColumnNameSet.add(str);
			}

			String shareColumnNameSuffixes = props.getProperty("share_column_name_suffixes");
			strArray = shareColumnNameSuffixes.split(",");
			for (String str : strArray) {
				dbToDicConfigure.shareColumnNameSuffixList.add(str);
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return dbToDicConfigure;
	}

}
