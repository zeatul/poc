package com.hawk.framework.utility.http;

import java.util.Map;

public interface HttpExecutor {
	
	public String get(String url,Map<String, String> params);
	
	public String post(String url, String content, Map<String, String> params);
	
	public String post(String url, byte[] b, Map<String, String> params);

}
