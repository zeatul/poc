package com.hawk.ecom.sms.service;

import java.text.MessageFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.ecom.sms.constant.ConstSmsStatus;
import com.hawk.ecom.sms.exception.OverSpeedException;
import com.hawk.ecom.sms.exception.OverTimesException;
import com.hawk.ecom.sms.persist.domain.TaskDomain;
import com.hawk.ecom.sms.request.SendVeriCodeParam;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.cache.CacheService;
import com.hawk.framework.pub.constant.ConstBoolean;
import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class SmsService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SmsOuterCallService smsOuterCallService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	@Qualifier("smsBatchCodeSequenceService")
	private PkGenService batchCodeSequenceService;
	
	@Autowired
	@Qualifier("pkGenService")
	private PkGenService pkGenService;
	
	@Autowired
	private TaskService taskService;
	
	public SmsService(){
		
	}
	
	private String buildVeriCodeCacheKey(String mobileNumber){
		return StringTools.concatWithSymbol("_", "sms","veriCode",mobileNumber);
	}
	
	public static class V {
		public long getStdt() {
			return stdt;
		}
		public void setStdt(long stdt) {
			this.stdt = stdt;
		}
		public int getTimes() {
			return times;
		}
		public void setTimes(int times) {
			this.times = times;
		}
		
		private int times;
		private long stdt;
	}
	
	
	/**
	 * 发送验证码
	 * @param mobileNumber
	 * @return
	 */
	@Valid
	public SendSmsResult sendVeriCode(@NotNull("整个参数") @Valid SendVeriCodeParam sendVeriCodeParam){
		final String mobileNumber = sendVeriCodeParam.getMobileNumber();
		/**
		 * 同一个手机号，发送间隔不低于1分钟
		 */
		String key = StringTools.concatWithSymbol("_", "sms","veriCode","interval",mobileNumber);
		if (cacheService.get(key, String.class)!=null){
			throw new OverSpeedException();
		}else{
			cacheService.put(key, "60",60);
		}
		
		/**
		 * 同一个手机号，一天只能发送5次 ,从第一次发送开始的时间开始计算
		 */

		
		String key2 = StringTools.concatWithSymbol("_", "sms","veriCode","timesperday",mobileNumber);
		
		
		
		V v = cacheService.get(key2, V.class);
		if (v!=null){
			int times = v.getTimes();
			if (times >= 5){
				throw  new OverTimesException();
			}else{
				times = times +1;
				v.setTimes(times);
				Long  expire = 3600*24 - (System.currentTimeMillis()-v.getStdt())/1000;
				cacheService.put(key2, v,Integer.parseInt(expire.toString()));
			}
		}else{
			v = new V();
			v.setTimes(1);
			v.setStdt(System.currentTimeMillis());
			
			cacheService.put(key2, v, 3600*24);
		}
		
		
		final String veriCode = StringTools.randomNumberString(4);
		logger.info("veriCode={}",veriCode);
		
		final String batchNo = new Long(batchCodeSequenceService.genPk()).toString();
		final MessageFormat messageformat = new MessageFormat("【飞到家权益平台】尊敬的用户，您的动态验证码为:{0}。该验证码有效期为{1}分钟。");
		final int validMinutes = 30;
		
		cacheService.put(buildVeriCodeCacheKey(mobileNumber), veriCode, validMinutes*60);
		
		final String message = messageformat.format(new Object[]{veriCode,validMinutes});
		SendSmsResult sendSmsResult = smsOuterCallService.send(mobileNumber, message, batchNo);
			
		/**
		 * 发送记录入库，后期可以改成日志
		 */
		TaskDomain taskDomain = new TaskDomain();
		Date curDate = new Date();
		taskDomain.setCreateDate(curDate);
		taskDomain.setUpdateDate(curDate);
		taskDomain.setExecTimes(1);
		taskDomain.setLastExecDate(curDate);
		taskDomain.setLastExecErrCode(sendSmsResult.getCode());
		taskDomain.setLastExecErrMsg(sendSmsResult.getMessage());
		taskDomain.setMaxExecTimes(1);
		taskDomain.setMobileNumber(mobileNumber);
		taskDomain.setSmsBatchNo(batchNo);
		taskDomain.setIsBatch(ConstBoolean.FALSE);
		taskDomain.setSmsModelCode("000001");
		taskDomain.setSmsMsgData(veriCode);
		taskDomain.setSmsOperatorCode("0001");
		taskDomain.setSmsReceipt(sendSmsResult.getReceipt());
		if (sendSmsResult.isSuccess()){
			taskDomain.setSmsStatus(ConstSmsStatus.SEND_SUCCESS);
		}else{
			taskDomain.setSmsStatus(ConstSmsStatus.SEND_FAILED);
		}
		taskDomain.setVersion(1);
		taskDomain.setId(pkGenService.genPk());
		
		logger.info("sendVeriCode={}",JsonTools.toJsonString(taskDomain));
		
		taskService.insert(taskDomain);
		
		
		
		return sendSmsResult;
	}
	
	/**
	 * 校验验证码
	 * @param mobileNumber
	 * @param veriCode
	 * @return
	 */
	public boolean checkVeriCode(String mobileNumber, String veriCode){
		if (StringTools.isNullOrEmpty(mobileNumber)){
			return false;
		}
		if (StringTools.isNullOrEmpty(veriCode)){
			return false;
		}
		String cachedVeriCode = cacheService.get(buildVeriCodeCacheKey(mobileNumber), String.class);
		if (veriCode.equals(cachedVeriCode)){
			cacheService.delete(buildVeriCodeCacheKey(mobileNumber));
			return true;
		}else{
			return false;
		}
	}

}
