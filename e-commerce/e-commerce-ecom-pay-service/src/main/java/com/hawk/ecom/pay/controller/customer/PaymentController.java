package com.hawk.ecom.pay.controller.customer;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hawk.ecom.pay.request.PayParam;
import com.hawk.ecom.pay.service.PaymentService;
import com.hawk.ecom.product.persist.domain.ProductDomain;
import com.hawk.ecom.product.request.CreateProductParam;
import com.hawk.ecom.product.request.ListProductParam;
import com.hawk.ecom.product.request.LoadProductParam;
import com.hawk.ecom.product.request.RemoveProductParam;
import com.hawk.ecom.product.request.UpdateProductParam;
import com.hawk.ecom.product.request.UpdateProductStatusParam;
import com.hawk.ecom.product.response.ProductInfoResponse;
import com.hawk.ecom.product.service.ProductService;
import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.pub.sql.PagingQueryResultWrap;
import com.hawk.framework.pub.web.HttpRequestTools;
import com.hawk.framework.pub.web.ResponseData;
import com.hawk.framework.pub.web.SuccessResponse;
import com.hawk.framework.pub.web.WebResponse;
import com.hawk.framework.utility.tools.DateTools;
import com.hawk.framework.utility.tools.DomainTools;

@RestController
@RequestMapping("/ecom/pay")
@CrossOrigin
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value = "/home", method = GET)
	public String home() {
		return "Welcome to /ecom/pay controller!!!" + ", current time = " + DateTools.convert(new Date(), DateTools.DATETIME_SSS_PATTERN);
	}

	@RequestMapping(value = "/wap", method = POST)
	public void wapPay(HttpServletRequest request ,HttpServletResponse response ) throws Exception {
		PayParam param = HttpRequestTools.parse(request, PayParam.class);
		param.setUserCode(AuthThreadLocal.getUserCode());
		param.setWap(true);
		String result = paymentService.trade(param);
	}

	
}
