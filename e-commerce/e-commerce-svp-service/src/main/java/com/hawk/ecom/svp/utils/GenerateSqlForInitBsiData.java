package com.hawk.ecom.svp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.persist.domain.BsiPhoneProductMapDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.framework.utility.tools.ResourceTools;

public class GenerateSqlForInitBsiData {
	
	
	private static Map<String,Integer> brandOrderMap = new HashMap<String,Integer>();
	static{

		brandOrderMap.put("苹果", 100);
		brandOrderMap.put("iPad", 200);
		brandOrderMap.put("三星", 300);
		brandOrderMap.put("华为", 400);
		brandOrderMap.put("oppo", 500);
		brandOrderMap.put("vivo", 600);
		brandOrderMap.put("魅族", 700);
		brandOrderMap.put("锤子", 800);
	}
	
	private static int getBrandOrder(String brand){
		brand = brand.toLowerCase();
		Integer i = brandOrderMap.get(brand);
		if (i == null)
			i = Integer.MAX_VALUE;
		return i;
	}

	public static void main(String[] args) {

		generate();

	}

	public static void generate() {

//		List<String> list = ResourceTools.readResource("/file/小宝碎屏险产品手机信号对应表20170321.csv", GenerateSqlForInitBsiData.class);
		
//		List<String> list = ResourceTools.readResource("/file/小宝测试环境数据.csv", GenerateSqlForInitBsiData.class);
		
		List<String> list = ResourceTools.readResource("/file/小宝正式环境数据.csv", GenerateSqlForInitBsiData.class);

		// list.forEach(e -> System.out.println(e));

		List<BsiPhoneModelDomain> phoneList = new ArrayList<BsiPhoneModelDomain>();
		Map<String, BsiPhoneModelDomain> phoneFilter = new HashMap<String, BsiPhoneModelDomain>();
		List<BsiPhoneProductMapDomain> mapList = new ArrayList<BsiPhoneProductMapDomain>();
		List<BsiProductDomain> productList = new ArrayList<BsiProductDomain>();
		Map<String, BsiProductDomain> productFilter = new HashMap<String, BsiProductDomain>();
		list.forEach(e -> {
			String[] strArray = e.split(",");
			
			if (strArray.length < 7)
				return;

			Integer phoneModelId = null;
			try {
				phoneModelId = Integer.parseInt(strArray[0].trim());
			} catch (NumberFormatException ex) {
				phoneModelId = Integer.parseInt(strArray[0].trim().substring(1, strArray[0].trim().length()));
			}

			String phoneBrand = strArray[1].trim();	
			String phoneModel = strArray[2].trim();					
			String productName = strArray[3].trim();
			Integer productId = Integer.parseInt(strArray[4]);
			BigDecimal bsiTradePrice = new BigDecimal(strArray[5].trim());
			Integer period = Integer.parseInt(strArray[6].trim().trim().replace("个月", ""));
			BigDecimal bsiRetailPrice = new BigDecimal(strArray[7].trim());
			BigDecimal bsiDisplayPrice = new BigDecimal(strArray[8].trim());

			String key = phoneModelId.toString();
			BsiPhoneModelDomain phoneModelDomain = phoneFilter.get(key);
			if (phoneModelDomain == null) {
				phoneModelDomain = new BsiPhoneModelDomain();
				phoneModelDomain.setBsiPhoneModelId(phoneModelId);
				phoneModelDomain.setBsiPhoneBrand(phoneBrand);
				phoneModelDomain.setBsiPhoneModel(phoneModel);
				phoneModelDomain.setBsiPhoneModelStatus(1);
				phoneList.add(phoneModelDomain);
				phoneFilter.put(key, phoneModelDomain);
			}

			BsiPhoneProductMapDomain phoneProductMapDomain = new BsiPhoneProductMapDomain();
			phoneProductMapDomain.setBsiPhoneModelId(phoneModelId);
			phoneProductMapDomain.setBsiProductId(productId);
			phoneProductMapDomain.setBsiProductValidPeriod(period);
			mapList.add(phoneProductMapDomain);

			key = productId.toString();
			BsiProductDomain productDomain = productFilter.get(key);
			if (productDomain == null) {
				productDomain = new BsiProductDomain();
				productDomain.setBsiProductId(productId);
				productDomain.setBsiProductName(productName);
				productDomain.setBsiProductStatus(1);
				productDomain.setBsiProductValidPeriod(period);
				productDomain.setBsiTradePrice(bsiTradePrice);
				productDomain.setBsiDisplayPrice(bsiDisplayPrice);
				productDomain.setBsiRetailPrice(bsiRetailPrice);
				productFilter.put(key, productDomain);
				productList.add(productDomain);
			}
		});

		System.out.println("delete from t_svp_bsi_phone_model;");
		phoneList.forEach(phoneModelDomain -> {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_svp_bsi_phone_model(bsi_phone_model_id,bsi_phone_brand,bsi_phone_model,bsi_phone_model_status) ")//
					.append("values(")//
					.append(phoneModelDomain.getBsiPhoneModelId()).append(",")//
					.append("'").append(phoneModelDomain.getBsiPhoneBrand()).append("'").append(",")//
					.append("'").append(phoneModelDomain.getBsiPhoneModel()).append("'").append(",")//
					.append(phoneModelDomain.getBsiPhoneModelStatus())//
					.append(");");
			System.out.println(sb.toString());
		});

		System.out.println("delete from t_svp_bsi_product;");
		productList.forEach(productDomain -> {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_svp_bsi_product(bsi_product_id,bsi_product_name,bsi_product_valid_period,bsi_product_status,bsi_trade_price,bsi_retail_price,bsi_display_price) ")//
					.append("values(")//
					.append(productDomain.getBsiProductId()).append(",")//
					.append("'").append(productDomain.getBsiProductName()).append("'").append(",")//
					.append(productDomain.getBsiProductValidPeriod()).append(",")//
					.append(1).append(",")//
					.append(productDomain.getBsiTradePrice()).append(",")//
					.append(productDomain.getBsiRetailPrice()).append(",")//
					.append(productDomain.getBsiDisplayPrice())//
					.append(");");
			System.out.println(sb.toString());
		});

		System.out.println("delete from  t_svp_bsi_phone_product_map;");
		mapList.forEach(phoneProdcutMapDomain -> {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_svp_bsi_phone_product_map(bsi_product_id,bsi_phone_model_id,bsi_product_valid_period) ")//
					.append("values(")//
					.append(phoneProdcutMapDomain.getBsiProductId()).append(",")//
					.append(phoneProdcutMapDomain.getBsiPhoneModelId()).append(",")//
					.append(phoneProdcutMapDomain.getBsiProductValidPeriod())//
					.append(");");
			System.out.println(sb.toString());
		});

		System.out.println("delete from  t_svp_bsi_phone_brand;");
		System.out.println("insert into t_svp_bsi_phone_brand(bsi_phone_brand,spell_abbr,object_order,bsi_phone_brand_status) SELECT distinct bsi_phone_brand ,'aaa',100,1 FROM t_svp_bsi_phone_model WHERE bsi_phone_model_status = 1;") ;
	}

}
