package com.hawk.ecom.codegen.sms;

import com.hawk.framework.codegen.database.DatabaseToDicXmlService;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForSmsApp {
	
	public static void main(String[] args){
		try {
			new DatabaseToDicXmlService().execute("com.hawk.ecom.codegen.sms");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
