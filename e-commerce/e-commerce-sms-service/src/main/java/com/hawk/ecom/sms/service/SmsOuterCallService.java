package com.hawk.ecom.sms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;

@Service
public class SmsOuterCallService {
	
	public SmsOuterCallService() throws Exception{
		httpExecutor = new HttpClientExecutorImpl();
	}
	
	private HttpExecutor httpExecutor ;
	
	public void send (String mobileNumber ,String message){
		
	}
	
	public void send (List<String> mobileNumberList,String message){
		
	}

}
