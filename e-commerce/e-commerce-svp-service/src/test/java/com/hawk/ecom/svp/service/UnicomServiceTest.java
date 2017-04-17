package com.hawk.ecom.svp.service;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class UnicomServiceTest {
	
	private UnicomService unicomService;
	
	@Before
	public void setup() throws Exception{
		unicomService = new UnicomService();
	}
	
//	@Test
	public void testIsUnicomMobileNumber() throws Exception{
		String mobileNumber = "13916082481";
		boolean result = unicomService.isUnicomMobileNumber(mobileNumber);
		
		System.out.println(mobileNumber + "is " + result);
		
		mobileNumber = "15618738059";
		result = unicomService.isUnicomMobileNumber(mobileNumber);
		
		System.out.println(mobileNumber + "is " + result);
	}
	
	@Test
	public void testChargeVirtualMobileData() throws Exception{
		String taskId = UUID.randomUUID().toString();
		String mobileNumber = "15618738059";
		int mobileDataSize = 10;
		unicomService.chargeVirtualMobileData(taskId, mobileNumber, mobileDataSize);
	}

}
