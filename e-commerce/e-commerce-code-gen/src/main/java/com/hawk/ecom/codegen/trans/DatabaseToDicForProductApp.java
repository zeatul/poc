package com.hawk.ecom.codegen.trans;

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

			SynonymHelper.addWord("id", "order_id", "订单主键");
			SynonymHelper.addWord("id", "order_detail_id", "订单明细主键");
			
			
			new DbToDicService("com.hawk.ecom.codegen.trans").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
