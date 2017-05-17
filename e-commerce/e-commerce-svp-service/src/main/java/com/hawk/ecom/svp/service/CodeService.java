package com.hawk.ecom.svp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hawk.framework.pub.pk.PkGenService;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class CodeService {
	
	@Autowired
	@Qualifier("cashCouponCodeSequenceService")
	private PkGenService cashCouponCodeSequenceService;
	
	@Autowired
	@Qualifier("orderCodeSequenceService")
	private PkGenService orderCodeSequenceService;
	
	@Autowired
	@Qualifier("chargeCodeSequenceService")
	private PkGenService chargeCodeSequenceService ;
	
	@Autowired
	@Qualifier("bsiTaskCodeSequenceService")
	private PkGenService bsiTaskCodeSequenceService;

	public String buildCashCouponCode(){
		return DateTools.convert(new Date(), "yyyyMMdd")+(cashCouponCodeSequenceService.genPk()+1000000);
		 
	}
	
	public String buildOrderCode(){
		return DateTools.convert(new Date(), "yyyyMMddHHmm")+(orderCodeSequenceService.genPk()+1000000);
	}
	
	public String buildChargeTaskCode(){
		return DateTools.convert(new Date(), "yyyyMMddHHmm")+(chargeCodeSequenceService.genPk()+1000000);
	}
	
	public String buildBsiTaskCode(){
		return DateTools.convert(new Date(), "yyyyMMddHHmm")+(bsiTaskCodeSequenceService.genPk()+1000000);
	}
	
	
	
}
