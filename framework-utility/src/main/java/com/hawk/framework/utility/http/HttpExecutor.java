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
	
	public String post(String url, Object object , List<HttpParam> params);
	
	public String post(String url, byte[] b, List<HttpParam> params);
	
	/**
	 * 小宝专用 ，将参数以特殊格式放在post请求里
	 * @param url
	 * @param content
	 * @param params
	 * @return
	 */
	public String postParamInBody(String url, String content, List<HttpParam> params);
	
	public String buildUrl(String url,List<HttpParam> params);

}
