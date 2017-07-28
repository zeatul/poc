package com.hawk.ecom.pay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hawk.ecom.pay.persist.domain.AlipayInfoDomain;
import com.hawk.ecom.pay.persist.mapper.AlipayInfoMapper;
import com.hawk.ecom.pay.request.AlipayNotifyParam;
import com.hawk.ecom.pay.request.AlipayTradeParam;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.NotNull;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.utility.tools.DomainTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

@Service
public class AlipayService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AlipayClient alipayClient;
	
	@Autowired
	private AlipayInfoMapper alipayInfoMapper;

	/**
	 * 查询交易状态
	 * 
	 * @param outTradeCode
	 * @throws Exception 
	 */
	@Valid
	public AlipayTradeQueryResponse query(@NotEmpty String outTradeCode) throws Exception {
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(outTradeCode);
		alipayRequest.setBizModel(model);

		AlipayTradeQueryResponse alipayResponse = alipayClient.execute(alipayRequest);
		
		logger.info("AlipayTradeQueryResponse = {}",JsonTools.toJsonString(alipayResponse));
		
		alipayResponse.getTradeStatus();
		alipayResponse.getOutTradeNo();
		alipayResponse.getTradeNo();
//		交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、TRADE_SUCCESS（交易支付成功）、TRADE_FINISHED（交易结束，不可退款）
		
		return alipayResponse;
	}
	
	
	
	@Valid
	public void notify(@NotNull("支付宝通知参数")AlipayNotifyParam alipayNotifyParam) throws Exception{
		AlipayInfoDomain alipayInfoDomain = DomainTools.copy(alipayNotifyParam, AlipayInfoDomain.class);
		alipayInfoMapper.insert(alipayInfoDomain);
	}

	/**
	 * wap支付
	 * 
	 * @param alipayTradeWapParam
	 * @return
	 * @throws Exception
	 */
	@Valid
	public String wapPay(@NotNull("支付宝支付参数") @Valid AlipayTradeParam alipayTradeWapParam) throws Exception {

		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

		model.setOutTradeNo(alipayTradeWapParam.getOutTradeNo());
		
		String subject = alipayTradeWapParam.getSubject();
		if (subject.length() > 256){
			subject = subject.substring(0, 128);
		}
		model.setSubject(subject);
		
		
		model.setTotalAmount(alipayTradeWapParam.getTotalAmount().toString());
		
		String body = alipayTradeWapParam.getBody();
		if (StringTools.isNotNullOrEmpty(body)){
			if (body.length() > 128){
				body = body.substring(0, 128);
			}
		}
		model.setBody(body);
		
		model.setProductCode("QUICK_WAP_PAY");

		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();

		alipayRequest.setBizModel(model);
		// 设置异步通知地址
		alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
		// 设置同步地址
		alipayRequest.setReturnUrl(AlipayConfig.RETURN_URL);

		String form = alipayClient.pageExecute(alipayRequest).getBody();

		return form;

	}

}
