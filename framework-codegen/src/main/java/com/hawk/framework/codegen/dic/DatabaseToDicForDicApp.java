package com.hawk.framework.codegen.dic;

import com.hawk.framework.codegen.database.DatabaseToDicService;

public class DatabaseToDicForDicApp {

	public static void main(String[] args) {
		try {
			new DatabaseToDicService().execute("com.hawk.framework.codegen.dic");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
