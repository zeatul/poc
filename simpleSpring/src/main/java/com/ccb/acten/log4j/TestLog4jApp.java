package com.ccb.acten.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog4jApp {
	
	private final static Logger logger = LoggerFactory.getLogger(TestLog4jApp.class);
	
	public static void main(String[] args){
		logger.debug("debug message");
		logger.warn("warn message");
		String filename = "c:/a.txt";
		logger.info("file not found,filepath={}",filename);
		logger.info("file not found,filepath="+filename);
		try{
			throw new Exception("myerror")	;		
		}catch(Exception ex){
			logger.error("found error",ex);
		}
	}

}
