package com.hawk.framework.codegen.dic;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 将数据字典表转换成数据库记录
 * 
 * @author Administrator
 *
 */
public class DatabaseToDicForDicApp {
	public static void main(String[] args) {
		try {
			
			SynonymHelper.addWord("object_id", "table_object_id", "表对象id");
			SynonymHelper.addWord("object_id", "fk_object_id", "外键对象id");
			SynonymHelper.addWord("object_id", "parent_column_object_id", "主表字段对象id");
			SynonymHelper.addWord("object_id", "child_column_object_id", "子表字段对象id");
			SynonymHelper.addWord("object_id", "word_object_id", "单词对象id");
			SynonymHelper.addWord("object_id", "index_object_id", "索引对象id");
			SynonymHelper.addWord("object_id", "column_object_id", "字段对象id");
			SynonymHelper.addWord("object_id", "application_object_id", "应用对象id");
			SynonymHelper.addWord("object_id", "parent_table_object_id", "主表对象id");
			SynonymHelper.addWord("object_id", "child_table_object_id", "子表对象id");

			new DbToDicService("com.hawk.framework.codegen.dic").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
