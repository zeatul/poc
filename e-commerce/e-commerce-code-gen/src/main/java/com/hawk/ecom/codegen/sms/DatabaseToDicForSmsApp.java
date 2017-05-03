package com.hawk.ecom.codegen.sms;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForSmsApp {
	
	public static void main(String[] args){
		try {
			SynonymHelper.addWord("id", "sms_task_id", "短信任务id");
			new DbToDicService("com.hawk.ecom.codegen.sms").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
