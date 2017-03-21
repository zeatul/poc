package com.hawk.framework.utility.http;

import java.util.Map;

public interface HttpExecutor {
	
	public String get(String path,Map<String, String> params);
	
	public String post(String path, String content, Map<String, String> params);
	
	public String post(String path, byte[] b, Map<String, String> params);

}
