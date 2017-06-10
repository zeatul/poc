package com.hawk.ecom.mall.persist.mapperex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hawk.ecom.mall.persist.domain.MallUserDomain;

public interface MallRoleUserExMapper {
	public int hasAnyRole(@Param("userCode") String userCode, @Param("roles") List<String> roles);

	public List<MallUserDomain> listMembersInRole(Map<String,Object> params);

}
