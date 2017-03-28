package com.hawk.framework.utility.http;

import java.util.List;

public interface HttpExecutor {
	
	public static class HttpParam{
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		private String key;
		private String value;
		
		public HttpParam(String key,String value){
			this.key = key;
			this.value = value;
		}
	}
	
	public String get(String url,List<HttpParam> params);
	
	public String post(String url, String content, List<HttpParam> params);
	
	public String post(String url, byte[] b, List<HttpParam> params);
	
	public String buildUrl(String url,List<HttpParam> params);

}
