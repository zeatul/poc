package com.hawk.ecom.sms.persist.job;

import java.util.List;

public class SendMessageJob implements Runnable{
	
	private List<String> mobileNumberList;
	private String message;
	
	

	public SendMessageJob(List<String> mobileNumberList, String message) {
		this.mobileNumberList = mobileNumberList;
		this.message = message;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
