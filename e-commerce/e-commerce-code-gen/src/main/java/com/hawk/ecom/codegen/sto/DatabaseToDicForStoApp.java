package com.hawk.ecom.codegen.sto;

import com.hawk.framework.codegen.database.DatabaseToDicXmlService;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForStoApp {
	
	public static void main(String[] args){
		try {
			new DatabaseToDicXmlService().execute("com.hawk.ecom.codegen.sto");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
