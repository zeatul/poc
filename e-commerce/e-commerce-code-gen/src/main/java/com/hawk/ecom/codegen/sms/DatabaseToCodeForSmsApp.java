package com.hawk.ecom.codegen.sms;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

/**
 * 从数据库生成代码(domain,sql,mapper)
 * @author pzhang1
 *
 */
public class DatabaseToCodeForSmsApp {
	
	public static void main(String[] args) {
		try {
			new DatabaseToCodeService().execute("com.hawk.ecom.codegen.sms");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
