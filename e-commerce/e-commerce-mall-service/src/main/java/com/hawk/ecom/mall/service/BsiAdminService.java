package com.hawk.ecom.mall.service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawk.ecom.mall.exception.IllegalAccessRuntimeException;
import com.hawk.ecom.mall.persist.mapperex.BsiAdminExMapper;
import com.hawk.ecom.mall.request.BsiOrderDetailReportParam;
import com.hawk.ecom.mall.request.BsiStatCouponParam;
import com.hawk.ecom.mall.request.BsiStatOrderDetailParam;
import com.hawk.ecom.pub.web.AuthThreadLocal;
import com.hawk.framework.dic.validation.annotation.NotEmpty;
import com.hawk.framework.dic.validation.annotation.Valid;
import com.hawk.framework.pub.sql.MybatisParam;
import com.hawk.framework.pub.sql.MybatisTools;
import com.hawk.framework.utility.tools.DateTools;

@Service
public class BsiAdminService {
	
	@Autowired
	private MallAuthService authService;
	
	@Autowired
	private BsiAdminExMapper bsiAdminExMapper;
	
	@Valid
	public List<HashMap<String,Object>> reportOrderDetail(@NotEmpty("参数") @Valid BsiOrderDetailReportParam bsiOrderDetailReportParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		Date stdt = DateTools.firstTimeOfDay( bsiOrderDetailReportParam.getStdt());
		Date endt = DateTools.firstTimeOfNextDay( bsiOrderDetailReportParam.getEndt());
		
		MybatisParam params = new MybatisParam().put("stdt", stdt).put("endt", endt);
		MybatisTools.page(params, bsiOrderDetailReportParam);
		
		return bsiAdminExMapper.reportOrderDetail(params);
	}
	
	@Valid
	public  List<HashMap<String,Object>> statCoupon(@NotEmpty("参数") @Valid BsiStatCouponParam bsiStatCouponParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		Date stdt = DateTools.firstTimeOfDay( bsiStatCouponParam.getStdt());
		Date endt = DateTools.firstTimeOfNextDay( bsiStatCouponParam.getEndt());
		MybatisParam params = new MybatisParam().put("stdt", stdt).put("endt", endt);
		
		return bsiAdminExMapper.statCoupon(params);
	}
	
	@Valid
	public  List<HashMap<String,Object>> statBsiOrderDetail(@NotEmpty("参数") @Valid BsiStatOrderDetailParam bsiStatOrderDetailParam){
		if (!authService.hasAnyRole(AuthThreadLocal.getUserCode(), Arrays.asList("admin"))){
			throw new IllegalAccessRuntimeException();
		}
		
		Date stdt = DateTools.firstTimeOfDay( bsiStatOrderDetailParam.getStdt());
		Date endt = DateTools.firstTimeOfNextDay( bsiStatOrderDetailParam.getEndt());
		MybatisParam params = new MybatisParam().put("stdt", stdt).put("endt", endt);
		
		return bsiAdminExMapper.statCoupon(params);
	}

	
	
}
