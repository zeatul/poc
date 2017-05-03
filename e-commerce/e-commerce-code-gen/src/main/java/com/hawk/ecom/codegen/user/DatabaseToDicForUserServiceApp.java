package com.hawk.ecom.codegen.user;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForUserServiceApp {
	
	public static void main(String[] args){
		try {
			SynonymHelper.addWord("ip", "login_ip", "登录IP");
			SynonymHelper.addWord("ip", "register_ip", "注册IP");
			SynonymHelper.addWord("id", "user_id", "用户id");
			new DbToDicService("com.hawk.ecom.codegen.user").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
