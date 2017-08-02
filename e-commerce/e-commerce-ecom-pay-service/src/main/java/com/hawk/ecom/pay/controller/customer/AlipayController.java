package com.hawk.ecom.pay.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.internal.util.AlipaySignature;
import com.hawk.ecom.pay.request.AlipayNotifyParam;
import com.hawk.ecom.pay.request.NotifyParam;
import com.hawk.ecom.pay.service.AlipayConfig;
import com.hawk.ecom.pay.service.AlipayService;
import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.framework.pub.web.HttpResponseHandler;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.JsonTools;
import com.hawk.framework.utility.tools.StringTools;

@RestController
@RequestMapping("/ecom/pay/alipay")
@CrossOrigin
public class AlipayController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AlipayService alipayService;
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/pay/alipay controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	
	private String computeFromRequest(HttpServletRequest request ,String key) throws Exception{
		/**
		 * 生产环境使用
		 */
		return new String(request.getParameter(key).getBytes("ISO-8859-1"), "UTF-8");
		
		/**
		 * 单元测试用
		 */
//		return URLDecoder.decode(request.getParameter(key));
	}
	
	 

	@RequestMapping(value = "/wap/return", method = { POST, GET })
	public void wapReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		logger.info("Alipay return message = {}", JsonTools.toJsonString(params));

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		// 商户订单号

		String out_trade_no = computeFromRequest(request,"out_trade_no");

		// 支付宝交易号

		String trade_no = computeFromRequest(request,"trade_no"); 

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
		// boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String
		// publicKey, String charset, String sign_type)
		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");

		if (verify_result) {// 验证成功

			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码
			// 该页面可做页面美工编辑
			String result = "支付成功:out_trade_no=" + out_trade_no + ",trade_no=" + trade_no;
			HttpResponseHandler.printHtmlASAP(response, result);
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			//////////////////////////////////////////////////////////////////////////////////////////
		} else {
			// 该页面可做页面美工编辑
			String result = "验证失败";
			HttpResponseHandler.printHtmlASAP(response, result);
		}

	}

	@RequestMapping(value = "/notify", method = {POST,GET})
	public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//			 valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			
//			valueStr = URLDecoder.decode(valueStr); //测试用
			
			params.put(name, valueStr);
		}

		logger.info("Alipay notify message = {}", JsonTools.toJsonString(params));

		AlipayNotifyParam alipayNotifyParam = new AlipayNotifyParam();

		// 异步通知参数
		// 参数 参数名称 类型 必填 描述 范例

		// notify_time 通知时间 Date 是 通知的发送时间。格式为yyyy-MM-dd HH:mm:ss 2015-14-27 15:45:58
		String notify_time = computeFromRequest(request,"notify_time"); 
		alipayNotifyParam.setNotifyTime(StringTools.isNullOrEmpty(notify_time) ? null : DateTools.parse(notify_time, DateTools.DATETIME_PATTERN));
		// notify_type 通知类型 String(64) 是 通知的类型 trade_status_sync
		// notify_id 通知校验ID String(128) 是 通知校验ID
		// ac05099524730693a8b330c5ecf72da9786
		// app_id 开发者的app_id String(32) 是 支付宝分配给开发者的应用Id 2014072300007148
		String app_id = computeFromRequest(request,"app_id");  
		alipayNotifyParam.setAppId(app_id);

		// charset 编码格式 String(10) 是 编码格式，如utf-8、gbk、gb2312等 utf-8
		// version 接口版本 String(3) 是 调用的接口版本，固定为：1.0 1.0
		// sign_type 签名类型 String(10) 是 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
		// RSA2
		// sign 签名 String(256) 是 请参考异步返回结果的验签 601510b7970e52cc63db0f44997cf70e

		// trade_no 支付宝交易号 String(64) 是 支付宝交易凭证号 2013112011001004330000121536
		String trade_no = computeFromRequest(request,"trade_no");  
		alipayNotifyParam.setTradeNo(trade_no);

		// out_trade_no 商户订单号 String(64) 是 原支付请求的商户订单号 6823789339978248
		String out_trade_no = computeFromRequest(request,"out_trade_no");  
		alipayNotifyParam.setOutTradeNo(out_trade_no);

		// out_biz_no 商户业务号 String(64) 否 商户业务ID，主要是退款通知中返回退款申请的流水号 HZRF001
		String out_biz_no = computeFromRequest(request,"out_biz_no");  
		alipayNotifyParam.setOutBizNo(out_biz_no);

		// buyer_id 买家支付宝用户号 String(16) 否 买家支付宝账号对应的支付宝唯一用户号。以2088开头的纯16位数字
		// 2088102122524333
		String buyer_id = computeFromRequest(request,"buyer_id");   
		alipayNotifyParam.setBuyerId(buyer_id);

		// buyer_logon_id 买家支付宝账号 String(100) 否 买家支付宝账号 15901825620
		String buyer_logon_id = computeFromRequest(request,"buyer_logon_id");  
		alipayNotifyParam.setBuyerLogonId(buyer_logon_id);

		// seller_id 卖家支付宝用户号 String(30) 否 卖家支付宝用户号 2088101106499364
		String seller_id = computeFromRequest(request,"seller_id");   
		alipayNotifyParam.setSellerId(seller_id);

		// seller_email 卖家支付宝账号 String(100) 否 卖家支付宝账号 zhuzhanghu@alitest.com
		String seller_email = computeFromRequest(request,"seller_email");   
		alipayNotifyParam.setSellerEmail(seller_email);

		// trade_status 交易状态 String(32) 否 交易目前所处的状态，见交易状态说明 TRADE_CLOSED
		String trade_status = computeFromRequest(request,"trade_status");    
		alipayNotifyParam.setTradeStatus(trade_status);

		// total_amount 订单金额 Number(9,2) 否 本次交易支付的订单金额，单位为人民币（元） 20
		String total_amount =  computeFromRequest(request,"total_amount");   
		alipayNotifyParam.setTotalAmount(StringTools.isNullOrEmpty(total_amount) ? null : new BigDecimal(total_amount));

		// receipt_amount 实收金额 Number(9,2) 否 商家在交易中实际收到的款项，单位为元 15
		String receipt_amount = computeFromRequest(request,"receipt_amount"); 
		alipayNotifyParam.setReceiptAmount(StringTools.isNullOrEmpty(receipt_amount) ? null : new BigDecimal(receipt_amount));

		// invoice_amount 开票金额 Number(9,2) 否 用户在交易中支付的可开发票的金额 10.00
		String invoice_amount = computeFromRequest(request,"invoice_amount");  
		alipayNotifyParam.setInvoiceAmount(StringTools.isNullOrEmpty(invoice_amount) ? null : new BigDecimal(invoice_amount));

		// buyer_pay_amount 付款金额 Number(9,2) 否 用户在交易中支付的金额 13.88
		String buyer_pay_amount = computeFromRequest(request,"buyer_pay_amount"); 
		alipayNotifyParam.setBuyerPayAmount(StringTools.isNullOrEmpty(buyer_pay_amount) ? null : new BigDecimal(buyer_pay_amount));

		// point_amount 集分宝金额 Number(9,2) 否 使用集分宝支付的金额 12.00
		String point_amount = computeFromRequest(request,"point_amount"); 
		alipayNotifyParam.setPointAmount(StringTools.isNullOrEmpty(point_amount) ? null : new BigDecimal(point_amount));

		// refund_fee 总退款金额 Number(9,2) 否 退款通知中，返回总退款金额，单位为元，支持两位小数 2.58
		String refund_fee = computeFromRequest(request,"refund_fee");  
		alipayNotifyParam.setRefundFee(StringTools.isNullOrEmpty(refund_fee) ? null : new BigDecimal(refund_fee));

		// subject 订单标题 String(256) 否 商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来
		// 当面付交易
		String subject = computeFromRequest(request,"subject");  
		alipayNotifyParam.setSubject(subject);

		// body 商品描述 String(400) 否 该订单的备注、描述、明细等。对应请求时的body参数，原样通知回来 当面付交易内容
		String body = computeFromRequest(request,"body");   
		alipayNotifyParam.setBody(body);

		// gmt_create 交易创建时间 Date 否 该笔交易创建的时间。格式为yyyy-MM-dd HH:mm:ss 2015-04-27
		// 15:45:57
		String gmt_create = computeFromRequest(request,"gmt_create");    
		alipayNotifyParam.setGmtCreate(StringTools.isNullOrEmpty(gmt_create) ? null : DateTools.parse(gmt_create, DateTools.DATETIME_PATTERN));

		// gmt_payment 交易付款时间 Date 否 该笔交易的买家付款时间。格式为yyyy-MM-dd HH:mm:ss
		// 2015-04-27 15:45:57
		String gmt_payment = computeFromRequest(request,"gmt_payment"); 
		alipayNotifyParam.setGmtPayment(StringTools.isNullOrEmpty(gmt_payment) ? null : DateTools.parse(gmt_payment, DateTools.DATETIME_PATTERN));

		// gmt_refund 交易退款时间 Date 否 该笔交易的退款时间。格式为yyyy-MM-dd HH:mm:ss.S
		// 2015-04-28 15:45:57.320
		String gmt_refund = computeFromRequest(request,"gmt_refund"); 
		alipayNotifyParam.setGmtRefund(StringTools.isNullOrEmpty(gmt_refund) ? null : DateTools.parse(gmt_refund, DateTools.DATETIME_PATTERN));

		// gmt_close 交易结束时间 Date 否 该笔交易结束时间。格式为yyyy-MM-dd HH:mm:ss 2015-04-29
		// 15:45:57
		String gmt_close = computeFromRequest(request,"gmt_close");  
		alipayNotifyParam.setGmtClose(StringTools.isNullOrEmpty(gmt_close) ? null : DateTools.parse(gmt_close, DateTools.DATETIME_PATTERN));

		// fund_bill_list 支付金额信息 String(512) 否 支付成功的各个渠道金额信息，详见资金明细信息说明
		// [{"amount":"15.00","fundChannel":"ALIPAYACCOUNT"}]
		String fund_bill_list = computeFromRequest(request,"fund_bill_list");  
		alipayNotifyParam.setFundBillList(fund_bill_list);

		// passback_params 回传参数 String(512) 否
		// 公共回传参数，如果请求时传递了该参数，则返回给商户时会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
		// merchantBizType%3d3C%26merchantBizNo%3d2016010101111
		// voucher_detail_list 优惠券信息 String 否 本交易支付时所使用的所有优惠券信息，详见优惠券信息说明
		// [{"amount":"0.20","merchantContribute":"0.00","name":"一键创建券模板的券名称","otherContribute":"0.20","type":"ALIPAY_DISCOUNT_VOUCHER","memo":"学生卡8折优惠"]

		// 交易状态说明 枚举名称 枚举说明
		// WAIT_BUYER_PAY 交易创建，等待买家付款
		// TRADE_CLOSED 未付款交易超时关闭，或支付完成后全额退款
		// TRADE_SUCCESS 交易支付成功
		// TRADE_FINISHED 交易结束，不可退款

		// 通知触发条件 触发条件名 触发条件描述 触发条件默认值
		// TRADE_FINISHED 交易完成 false（不触发通知）
		// TRADE_SUCCESS 支付成功 true（触发通知）
		// WAIT_BUYER_PAY 交易创建 false（不触发通知）
		// TRADE_CLOSED 交易关闭 true（触发通知）

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		// 计算得出通知验证结果
//		boolean verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, "RSA2");
		boolean verify_result = true;
		if (verify_result) {
			// 验证成功
			logger.info("Succeeded to verify signature,trade_status={}", trade_status);
			//////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码

			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				// 如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

				try {
					alipayService.notify(alipayNotifyParam);
				} catch (Exception e) {
					logger.error("alipayService.notify() Failed", e);
				}
				NotifyParam notifyParam = new NotifyParam();
				notifyParam.setPaymentBillCode(alipayNotifyParam.getOutTradeNo());
				notifyParam.setTotalAmount(alipayNotifyParam.getTotalAmount());
				paymentService.notifySuccess(notifyParam);
			} else if (trade_status.equals("TRADE_CLOSED")) {
				
			}

			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

			HttpResponseHandler.printHtmlASAP(response, "success");

			//////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			logger.info("Failed to verify signature");
			HttpResponseHandler.printHtmlASAP(response, "fail");
		}
	}

}
