package com.hawk.ecom.codegen.product;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForProductApp {
	
	public static void main(String[] args){
		try {
//			SynonymHelper.addWord("user_code", "create_user_code", "创建用户编号");
//			SynonymHelper.addWord("user_code", "update_user_code", "更新用户编号");
//			SynonymHelper.addWord("user_code", "delete_user_code", "删除用户编号");
//			SynonymHelper.addWord("id", "user_id", "用户id");
//			SynonymHelper.addWord("id", "role_id", "角色id");
//			SynonymHelper.addWord("id", "pid", "父id");
//			SynonymHelper.addWord("id", "right_id", "权限id");
			
			SynonymHelper.addWord("id", "product_id", "产品主键");
			SynonymHelper.addWord("id", "attr_name_id", "属性名主键");
			SynonymHelper.addWord("id", "attr_value_id", "属性值主键");
			SynonymHelper.addWord("id", "ownert_id", "所有者主键");
			SynonymHelper.addWord("id", "sku_id", "sku主键");
			SynonymHelper.addWord("id", "category_id", "产品目录主键");
			SynonymHelper.addWord("id", "category_id", "权限id");
			SynonymHelper.addWord("id", "supplier_id", "权限id");
			
			
			new DbToDicService("com.hawk.ecom.codegen.product").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
