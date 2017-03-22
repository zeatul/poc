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

		List<String> list = ResourceTools.readResource("/file/小宝碎屏险产品手机信号对应表20170321.csv", GenerateSqlForInitBsiData.class);

		List<BsiPhoneModelDomain> phoneList = new ArrayList<BsiPhoneModelDomain>();
		List<BsiPhoneProdcutMapDomain> mapList = new ArrayList<BsiPhoneProdcutMapDomain>();
		List<BsiProductDomain> productList = new ArrayList<BsiProductDomain>();
		Map<String,BsiProductDomain> filter = new HashMap<String,BsiProductDomain>();
		list.forEach(e -> {
			String [] strArray = e.split(",");
			long phoneModelId = Long.parseLong(strArray[0]);
			String phoneBrand = strArray[1];
			String phoneModel = strArray[2];
			String productName = strArray[3];
			Long productId = Long.parseLong(strArray[4]);
			int period = Integer.parseInt(strArray[5]);
			
			BsiPhoneModelDomain  phoneModelDomain = new BsiPhoneModelDomain();
			phoneModelDomain.setBsiPhoneModelId(phoneModelId);
			phoneModelDomain.setBsiPhoneBrand(phoneBrand);
			phoneModelDomain.setBsiPhoneModel(phoneModel);
			phoneModelDomain.setBsiPhoneModelStatus("1");			
			phoneList.add(phoneModelDomain);
			
			BsiPhoneProdcutMapDomain phoneProdcutMapDomain = new BsiPhoneProdcutMapDomain();
			phoneProdcutMapDomain.setBsiPhoneModelId(phoneModelId);
			phoneProdcutMapDomain.setBsiProductId(productId);			
			mapList.add(phoneProdcutMapDomain);
			
			String key = productId.toString();
			BsiProductDomain productDomain =  filter.get(key);
			if (productDomain == null){
				productDomain = new BsiProductDomain();
				productDomain.setBsiProductId(productId);
				productDomain.setBsiProductName(productName);
				productDomain.setBsiProductStatus("1");
				productDomain.setBsiProducValidPeriod(period);
				filter.put(key, productDomain);
				productList.add(productDomain);
			}
		});

	}

}
