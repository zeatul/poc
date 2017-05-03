package com.hawk.ecom.codegen.svp;

import com.hawk.framework.codegen.database.DbToDicService;
import com.hawk.framework.codegen.database.SynonymHelper;

/**
 * 从数据库生成数据字典
 * @author pzhang1
 *
 */
public class DatabaseToDicForSvpApp {
	
	public static void main(String[] args){
		try {
			SynonymHelper.addWord("price", "bsi_display_price", "显示价");
			SynonymHelper.addWord("price", "bsi_trade_price", "批发价");
			SynonymHelper.addWord("price", "bsi_retail_price", "零售价");		
//			addWord("id", "bsi_phone_model_id", "手机品牌型号ID");
//			addWord("id", "bsi_product_id", "小宝产品ID");
			SynonymHelper.addWord("id", "order_id", "订单ID");		
			SynonymHelper.addWord("mobile_number", "bsi_benef_mobile_number", "受益人手机号码");
			SynonymHelper.addWord("mobile_number", "charge_mobile_number", "充值手机号码");
			new DbToDicService("com.hawk.ecom.codegen.svp").execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}

}
