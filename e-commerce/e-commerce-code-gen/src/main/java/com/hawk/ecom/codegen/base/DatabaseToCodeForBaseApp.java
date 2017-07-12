package com.hawk.ecom.codegen.base;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

/**
 * 从数据库生成代码(domain,sql,mapper)
 * @author pzhang1
 *
 */
public class DatabaseToCodeForBaseApp {
	
	public static void main(String[] args) {
		try {
			new DatabaseToCodeService().execute("com.hawk.ecom.codegen.base");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
