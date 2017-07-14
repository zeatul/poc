package com.hawk.ecom.codegen.pay;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForPayApp {
	
	public static void main(String[] args){
		try {
//			SynonymHelper.addWord("user_code", "create_user_code", "创建用户编号");
//			SynonymHelper.addWord("user_code", "update_user_code", "更新用户编号");
//			SynonymHelper.addWord("user_code", "delete_user_code", "删除用户编号");
//			SynonymHelper.addWord("id", "user_id", "用户id");
//			SynonymHelper.addWord("id", "role_id", "角色id");
//			SynonymHelper.addWord("id", "pid", "父id");
//			SynonymHelper.addWord("id", "right_id", "权限id");
			
			new DbToDicService("com.hawk.ecom.codegen.pay").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
