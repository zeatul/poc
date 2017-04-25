package com.hawk.framework.codegen.dic;

import com.hawk.framework.codegen.database.DbToDicService;

/**
 * 将数据字典表转换成数据库记录
 * @author Administrator
 *
 */
public class DbToDicForDicApp {
	public static void main(String[] args) {
		try {
			new DbToDicService("com.hawk.framework.codegen.dic").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
