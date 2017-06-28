package com.hawk.ecom.query.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hawk.ecom.pub.response.MultiResponse;
import com.hawk.ecom.query.persist.domainex.SystemResourceExDomain;
import com.hawk.framework.pub.web.WebResponse;

public class ProductController {
	
	@RequestMapping(value = "/h5main", method = {POST,GET})
	public WebResponse<MultiResponse<SystemResourceExDomain>> h5main(HttpServletRequest request) throws Exception{
		select a.id, a.sku_name,a.thumbnail ,b.on_sale_stdt ,b.on_sale_endt from t_prd_sku a, t_prd_product b 
		where a.product_id = b.id and b.product_status = 100 and a.sku_status = 100 
		and b.on_sale_stdt <= now() and b.on_sale_endt > now()
	}

}
