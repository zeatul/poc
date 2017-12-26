package com.ccb.acten.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jObject {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void exec(){
		logger.error("error message");
	}

}
