package com.hawk.ecom.svp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hawk.ecom.svp.exception.OuterCallException;
import com.hawk.ecom.svp.utils.DES;
import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 联通的外部接口调用类
 * 在SvpOutCall里配置bean
 * @author Administrator
 *
 */
public class UnicomService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final HttpExecutor httpExecutor;

	private final String baseUrl = "http://58.250.151.66";

	private final String merchantName = "feidaojia";

	private final String merchantPwd = "feidaojia123456";

	private final String key = "$df@4G^4";

	public UnicomService() throws Exception {
		this.httpExecutor = new HttpClientExecutorImpl();
	}

	/**
	 * 虚拟流量充值
	 * 
	 * @param taskId
	 * @param mobileNumber
	 * @param chargeSize
	 * @throws Exception
	 */
	public void chargeVirtualMobileData(String taskId, String mobileNumber, int mobileDataSize) throws OuterCallException,Exception {
		logger.info("chargeVirtualMobileData start");
		// http://58.250.151.66/wowap-interface/flow/trafficOrder?u=taskId=111;merchantName=1;merchantPwd=1;mobile=18607511841;flowrateValue=10
		final String url = baseUrl + "/wowap-interface/flow/trafficOrder";
		String u = StringTools.getThreadSafeStringBuilder()//
				.append("taskId=").append(taskId).append(";")//
				.append("merchantName=").append(merchantName).append(";")//
				.append("merchantPwd=").append(merchantPwd).append(";")//
				.append("mobile=").append(mobileNumber).append(";")//
				.append("flowrateValue=").append(mobileDataSize).append(";")//
				.toString();//
		logger.info("chargeVirtualMobileData u={}", u);
		u = DES.encryptDES(u, key);
		logger.info("chargeVirtualMobileData encrypted u={}", u);
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("u", u));
		String result = httpExecutor.get(url, params);
		logger.info("chargeVirtualMobileData result={}", result);

		HashMap<?, ?> map = JsonTools.toObject(result, HashMap.class);

		String status = (String) map.get("status");
		if (!"success".equalsIgnoreCase(status)) {
			Integer code = (Integer) map.get("code");
			String msg = (String) map.get("msg");
			throw new OuterCallException(code, msg);
		}

	}

	/**
	 * 判断是否是联通手机号
	 * @param mobileNumber
	 * @return
	 * @throws Exception 
	 */
	public boolean isUnicomMobileNumber(String mobileNumber) throws Exception{
	//	http://58.250.151.66/wowap-interface/flow/isUnicom?mobileAndTime=18607511841;2017-03-24 15:46:55
		logger.info("isUnicomMobileNumber start");
		final String url = baseUrl + "/wowap-interface/flow/isUnicom";
		
		String mobileAndTime = StringTools.concatWithSymbol(";", mobileNumber,DateTools.convert(new Date(), DateTools.DATETIME_PATTERN));
		logger.info("isUnicomMobileNumber mobileAndTime={}",mobileAndTime);
		mobileAndTime = DES.encryptDES(mobileAndTime, key);
		logger.info("isUnicomMobileNumber encrypted mobileAndTime={}",mobileAndTime);
		
		List<HttpParam> params = new ArrayList<HttpParam>();
		params.add(new HttpParam("mobileAndTime",mobileAndTime));
		String result = httpExecutor.get(url, params);
		logger.info("isUnicomMobileNumber result={}", result);
		
		HashMap<?, ?> map = JsonTools.toObject(result, HashMap.class);
		String code = (String) map.get("code");
		if("1".equals(code))
			return true;
		
		return false;
	}

}
