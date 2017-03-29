package com.hawk.ecom.svp.service;

import org.springframework.stereotype.Service;

import com.hawk.ecom.svp.request.SignInParam;

@Service
public class MobileDataService {
	
	/**
	 * 签到,送流量
	 * 每个月可以签到10次，每次签到送10M流量。
	 * 只支持流通手机号。 每隔5分钟可以签到一次。
	 * 当月签到10次，即送完。
	 * 下个月重新开始签到计算
	 * @param signInParam
	 */
	public void signIn(SignInParam signInParam){
		
	}

}
