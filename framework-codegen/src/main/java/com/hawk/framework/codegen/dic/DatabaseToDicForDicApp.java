package com.hawk.framework.codegen.dic;

import com.hawk.framework.codegen.database.DatabaseToDicXmlService;

public class DatabaseToDicForDicApp {

	public static void main(String[] args) {
		try {
			new DatabaseToDicXmlService().execute("com.hawk.framework.codegen.dic");
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

}
