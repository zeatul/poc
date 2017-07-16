package com.hawk.ecom.outer.service;

import org.junit.Test;

public class ChargeDataServiceTest {
	
	@Test
	public void testCharge() throws Exception{
		ChargeDataService service = new ChargeDataService();
		
		service.charge("13916082481", "111", "1111");
	}

}
