package com.hawk.ecom.mall.persist.mapperex;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MallRoleUserExMapper {
	public int hasAnyRole(@Param("userCode") String userCode, @Param("roles") List<String> roles);
}
