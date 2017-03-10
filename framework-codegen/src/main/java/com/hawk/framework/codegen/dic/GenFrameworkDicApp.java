package com.hawk.framework.codegen.dic;

import com.hawk.framework.codegen.database.DatabaseToCodeService;

/**
 * 生成dic模块的数据库访问代码
 * @author pzhang1
 *
 */
public class GenFrameworkDicApp {
	
	public static void main(String[] args) {
		try {
			new DatabaseToCodeService().execute("com.hawk.framework.codegen.dic");
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
