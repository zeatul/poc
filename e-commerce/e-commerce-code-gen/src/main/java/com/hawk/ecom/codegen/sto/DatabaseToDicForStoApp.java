package com.hawk.ecom.codegen.sto;


import com.hawk.framework.codegen.database.DbToDicService;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForStoApp {
	
	public static void main(String[] args){
		try {
			new DbToDicService("com.hawk.ecom.codegen.sto").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
