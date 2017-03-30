package com.hawk.ecom.svp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hawk.ecom.svp.persist.domain.BsiPhoneModelDomain;
import com.hawk.ecom.svp.persist.domain.BsiPhoneProdcutMapDomain;
import com.hawk.ecom.svp.persist.domain.BsiProductDomain;
import com.hawk.framework.utility.tools.ResourceTools;

public class GenerateSqlForInitBsiData {

	public static void main(String[] args) {

		generate();

	}

	public static void generate() {

		List<String> list = ResourceTools.readResource("/file/小宝碎屏险产品手机信号对应表20170321.csv", GenerateSqlForInitBsiData.class);

		// list.forEach(e -> System.out.println(e));

		List<BsiPhoneModelDomain> phoneList = new ArrayList<BsiPhoneModelDomain>();
		Map<String, BsiPhoneModelDomain> phoneFilter = new HashMap<String, BsiPhoneModelDomain>();
		List<BsiPhoneProdcutMapDomain> mapList = new ArrayList<BsiPhoneProdcutMapDomain>();
		List<BsiProductDomain> productList = new ArrayList<BsiProductDomain>();
		Map<String, BsiProductDomain> productFilter = new HashMap<String, BsiProductDomain>();
		list.forEach(e -> {
			String[] strArray = e.split(",");

			Integer phoneModelId = null;
			try {
				phoneModelId = Integer.parseInt(strArray[0].trim());
			} catch (NumberFormatException ex) {
				phoneModelId = Integer.parseInt(strArray[0].trim().substring(1, strArray[0].trim().length()));
			}

			String phoneBrand = strArray[1].trim();

			String phoneModel = strArray[2].trim();
			String productName = strArray[3].trim();
			Integer productId = Integer.parseInt(strArray[4].trim());
			int period = Integer.parseInt(strArray[5].trim());

			String key = phoneModelId.toString();
			BsiPhoneModelDomain phoneModelDomain = phoneFilter.get(key);
			if (phoneModelDomain == null) {
				phoneModelDomain = new BsiPhoneModelDomain();
				phoneModelDomain.setBsiPhoneModelId(phoneModelId);
				phoneModelDomain.setBsiPhoneBrand(phoneBrand);
				phoneModelDomain.setBsiPhoneModel(phoneModel);
				phoneModelDomain.setBsiPhoneModelStatus("1");
				phoneList.add(phoneModelDomain);
				phoneFilter.put(key, phoneModelDomain);
			}

			BsiPhoneProdcutMapDomain phoneProdcutMapDomain = new BsiPhoneProdcutMapDomain();
			phoneProdcutMapDomain.setBsiPhoneModelId(phoneModelId);
			phoneProdcutMapDomain.setBsiProductId(productId);
			mapList.add(phoneProdcutMapDomain);

			key = productId.toString();
			BsiProductDomain productDomain = productFilter.get(key);
			if (productDomain == null) {
				productDomain = new BsiProductDomain();
				productDomain.setBsiProductId(productId);
				productDomain.setBsiProductName(productName);
				productDomain.setBsiProductStatus("1");
				productDomain.setBsiProductValidPeriod(period);
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
					.append("'").append(phoneModelDomain.getBsiPhoneModelStatus()).append("'")//
					.append(");");
			System.out.println(sb.toString());
		});

		System.out.println("delete from t_svp_bsi_product;");
		productList.forEach(productDomain -> {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_svp_bsi_product(bsi_product_id,bsi_product_name,bsi_product_valid_period,bsi_product_status) ")//
					.append("values(")//
					.append(productDomain.getBsiProductId()).append(",")//
					.append("'").append(productDomain.getBsiProductName()).append("'").append(",")//
					.append(productDomain.getBsiProductValidPeriod())//
					.append("'").append(productDomain.getBsiProductName()).append("'")//
					.append(");");
			System.out.println(sb.toString());
		});

		System.out.println("delete from  t_svp_bsi_phone_prodcut_map;");
		mapList.forEach(phoneProdcutMapDomain -> {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into t_svp_bsi_phone_prodcut_map(bsi_product_id,bsi_phone_model_id) ")//
					.append("values(")//
					.append(phoneProdcutMapDomain.getBsiProductId()).append(",")//
					.append(phoneProdcutMapDomain.getBsiPhoneModelId())//
					.append(");");
			System.out.println(sb.toString());
		});

	}

}
