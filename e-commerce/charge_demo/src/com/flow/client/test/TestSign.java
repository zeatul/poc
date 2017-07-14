package com.flow.client.test;

import com.flow.client.EncrpytionUtil;

public class TestSign {

	public static void main(String[] args) {

		String str = "clientCode=testclientOrderNo=2016070609362594phone=13052376285productCode=LLT200010notifyUrl=http://125.83.167.236:7892/hello";
		str="apitest13896512471391759277433";
		System.out.println(EncrpytionUtil.str2MD5(str));

	}

}
