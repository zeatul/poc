package com.hawk.ecom.svp.persist.mapperex;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.svp.persist.domain.OrderDomain;

public interface OrderExMapper {
	
	public List<OrderDomain> querySigninOrderOfMonth(@Param("mobileNumber") String mobileNumber ,@Param("orderType") String orderType , @Param("stdt") Date stdt ,@Param("endt") Date endt);

}
