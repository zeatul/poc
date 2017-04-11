package com.hawk.framework.pub.sql;

import java.util.HashMap;

public class MybatisParam extends HashMap<String,Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7322350305630590749L;
	
	
	public MybatisParam put(String key,Object value){
		this.put(key, value);
		return this;
	}

}
