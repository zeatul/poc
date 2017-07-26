package com.hawk.ecom.outer.service;

import java.util.UUID;

import org.junit.Test;

import com.hawk.framework.utility.tools.JsonTools;

public class ChargeDataServiceTest {
	
	@Test
	public void testCharge() throws Exception{
		ChargeDataService service = new ChargeDataService();
		
//		ChargeResult chargeResult = service.charge("18616550187", "LC020U21010M", UUID.randomUUID().toString().replaceAll("-", ""));
	
		ChargeResult chargeResult = service.charge("18666075617", "LC020U21010M", UUID.randomUUID().toString().replaceAll("-", ""));
		
		System.out.println("+++++++++++++ charge reulst=" +JsonTools.toJsonString(chargeResult	));

		
		
		QueryResult queryResult = service.queryChargeResult("21500360624408322496");
		
		System.out.println("+++++++++++++ query reulst=" +JsonTools.toJsonString(queryResult	));
	}

}
