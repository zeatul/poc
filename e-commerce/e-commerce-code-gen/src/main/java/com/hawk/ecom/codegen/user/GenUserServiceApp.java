package com.hawk.ecom.codegen.user;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

/**
 * 从数据库生成代码(domain,sql,mapper)
 * @author pzhang1
 *
 */
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
