package com.hawk.ecom.codegen.product;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

/**
 * 从数据库生成代码(domain,sql,mapper)
 * @author pzhang1
 *
 */
public class DatabaseToCodeForProductApp {
	
	public static void main(String[] args) {
		try {
			new DatabaseToCodeService().execute("com.hawk.ecom.codegen.product");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
