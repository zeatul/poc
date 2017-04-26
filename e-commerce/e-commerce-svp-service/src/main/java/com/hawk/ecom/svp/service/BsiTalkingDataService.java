package com.hawk.ecom.svp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;

@Service
public class BsiTalkingDataService {

	@Autowired
	private CacheService cacheService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String apikey = "698cd5baa506479d861d91a5361e3dac";
	private String apitoken = "0ae8dc8bc15045469f96e34a16d11224";

	private String cacheAccessTokenKey = "BsiTalkingDataService:AccessToken";

	@SuppressWarnings("rawtypes")
	private String getAccessToken() {

//		String accessToken = cacheService.get(cacheAccessTokenKey, String.class);
//		if (accessToken != null) {
//			logger.info("cached accessToken={}", accessToken);
//			return accessToken;
//		}

		String url = "https://api.talkingdata.com/tdmkaccount/authen/app/v2";
		HttpClientExecutorImpl httpExecutor = null;
		try {
			httpExecutor = new HttpClientExecutorImpl();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("apikey", apikey));
		params.add(new HttpParam("apitoken", apitoken));

		logger.info("getAccessToken param={}", JsonTools.toJsonString(params));
		String result = httpExecutor.get(url, params);

		logger.info("getAccessToken()={}", result);

		HashMap map = JsonTools.toObject(result, HashMap.class);
		Map data = (Map) (map.get("data"));
		Boolean flag = (Boolean) data.get("login");
		if (flag != null && flag) {
			String token = (String) (data.get("token"));
//			cacheService.put(cacheAccessTokenKey, token,60*55);
			return token;
		} else {
			throw new RuntimeException("Failed to login talking data server");
		}
	}

	@SuppressWarnings("rawtypes")
	public Date first(String imei) {
		String url = "https://api.talkingdata.com/data/tr/user-device-interval/v1/first";

		HttpClientExecutorImpl httpExecutor = null;
		try {
			httpExecutor = new HttpClientExecutorImpl();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		String accessToken = getAccessToken();
		httpExecutor.addHeader(new BasicHeader("X-Access-Token", accessToken));

		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("id", imei));
		params.add(new HttpParam("type", "imei"));

		logger.info("first param={}", JsonTools.toJsonString(params));
		String result = httpExecutor.get(url, params);
		logger.info("first()={}", result);
		HashMap map = JsonTools.toObject(result, HashMap.class);
		Integer code = (Integer) map.get("code");
		if (code != null && code == 2000) {
			throw new RuntimeException("Failed to call first()");
		}

		if (code != null && code == 2001) {
			Map data = (Map) (map.get("data"));
			String time = (String) data.get("time");
			return DateTools.parse(time, "yyyyMMdd");
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(new BsiTalkingDataService().first("1111"));
	}

}