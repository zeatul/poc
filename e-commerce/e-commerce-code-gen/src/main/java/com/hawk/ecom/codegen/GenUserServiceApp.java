package com.hawk.ecom.codegen;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

public class GenUserServiceApp {
	
	public static void main(String[] args) {
		try {
			new DatabaseToCodeService().execute("com.hawk.ecom.codegen.user");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
