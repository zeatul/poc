package com.hawk.ecom.sms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.message.BasicHeader;
import org.springframework.stereotype.Service;

import com.hawk.framework.utility.http.HttpClientExecutorImpl;
import com.hawk.framework.utility.http.HttpExecutor;
import com.hawk.framework.utility.http.HttpExecutor.HttpParam;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

/**
 * 易云短信接口服务
 * 
 * @author zhp
 *
 */
@Service
public class SmsOuterCallService {

	// 帐号： test04 密码：7TkYPkVh 网关地址：118.178.117.163

	private final String baseUrl = "http://118.178.117.163";
	private final String accNo = "test04";
	private final String password = "7TkYPkVh";
	private final Map<String, String> codeMsgMap = new HashMap<String, String>();

	private void initCodeMsgMap() {
		codeMsgMap.put("0", "成功");
		codeMsgMap.put("100", "系统忙（因平台侧原因，暂时无法处理提交的短信）");
		codeMsgMap.put("101", "无此用户/用户未登陆");
		codeMsgMap.put("102", "密码错");
		codeMsgMap.put("103", "提交过快（提交速度超过流速限制）");
		codeMsgMap.put("104", "未知错误");
		codeMsgMap.put("105", "敏感短信（短信内容包含敏感词）");
		codeMsgMap.put("106", "消息长度错（>500或<=0）");
		codeMsgMap.put("107", "无合法手机号码");
		codeMsgMap.put("108", "手机号码个数错");
		codeMsgMap.put("109", "无发送额度（该用户可用短信数已使用完）");
		codeMsgMap.put("111", "用户自定义扩展号超长");
		codeMsgMap.put("112", "无此产品，用户没有订购该产品");
		codeMsgMap.put("114", "签名在黑名单");
		codeMsgMap.put("115", "签名不合法，未带签名（用户必须带签名的前提下）");
		codeMsgMap.put("116", "IP 地址在黑名单内");
		codeMsgMap.put("117", "IP地址认证错,请求调用的IP地址不是系统登记的IP地址");
		codeMsgMap.put("118", "用户没有相应的发送权限");
		codeMsgMap.put("119", "用户已过期");
		codeMsgMap.put("121", "手机号码在黑名单");
		codeMsgMap.put("122", "手机号码不在白名单");
		codeMsgMap.put("124", "手机号码未找到对应运营商");
		codeMsgMap.put("125", "手机号码格式错误");
		codeMsgMap.put("126", "号码发送频率超速");
		codeMsgMap.put("199", "无此类型接口权限");
	}

	public SmsOuterCallService() throws Exception {
		HttpClientExecutorImpl httpClientExecutorImpl = new HttpClientExecutorImpl();
		httpExecutor = httpClientExecutorImpl;
		httpClientExecutorImpl.addHeader(new BasicHeader("Accept", "application/json"));
		httpClientExecutorImpl.addHeader(new BasicHeader("Content-Type", "application/json;charset=utf-8"));

	}

	private HttpExecutor httpExecutor;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SendSmsResult send(String mobileNumber, String message, String batchNo) {
		final String url = baseUrl + "/smsapi/SmsMt.php";
		List<HttpParam> paramList = new ArrayList<HttpParam>();
		paramList.add(new HttpParam("mobile", mobileNumber));
		paramList.add(new HttpParam("msg", message));
		paramList.add(new HttpParam("serialno", batchNo));
		paramList.add(new HttpParam("needstat", "0"));
		String timestamp = DateTools.convert(new Date(), "yyyyMMddHHmmss");
		String token = DigestUtils.md5Hex(StringTools.concat(accNo, timestamp, password));
		paramList.add(new HttpParam("token", token));
		String result = httpExecutor.get(url, paramList);

		/**
		 * { "RetTime": "20161110012051", "RetCode": "0", "Rets": [ { "RetCode":
		 * "0", "MsgId": "20161110821191390101", "Mobile": "15657376123" } ] }
		 * 
		 */

		HashMap map = JsonTools.toObject(result, HashMap.class);
		SendSmsResult sendSmsResult = new SendSmsResult();
		String code = (String) map.get("RetCode");
		sendSmsResult.setCode(code);
		sendSmsResult.setMessage(codeMsgMap.get(code));
		if (!"0".equals(code)) {
			sendSmsResult.setSuccess(false);
		} else {
			sendSmsResult.setSuccess(true);
			sendSmsResult.setMessage("成功");
			List<Map> list = (List<Map>) (map.get("Rets"));
			sendSmsResult.setReceipt((String) (list.get(0).get("MsgId")));
		}
		return sendSmsResult;

	}

	public void send (List<String> mobileNumberList,String message){
		
	}

	
}